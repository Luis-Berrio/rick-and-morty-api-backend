package com.example.rick_and_morty_api_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.rick_and_morty_api_backend.models.ApiResponse;
import com.example.rick_and_morty_api_backend.repository.CharacterRepository;
import com.example.rick_and_morty_api_backend.models.Character;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterService {

  @Autowired
  private CharacterRepository characterRepository;

  @Autowired
  private RestTemplate restTemplate;

  private static final String API_URL = "https://rickandmortyapi.com/api/character";

  public void fetchAndSaveCharacters(int totalItemsToFetch) {
    int fetchedItems = 0;
    int currentPage = 1;
    boolean hasMoreItems = true;
    List<Character> allCharacters = new ArrayList<>();

    while (hasMoreItems && fetchedItems < totalItemsToFetch) {
      String paginatedUrl = API_URL + "?page=" + currentPage;
      ResponseEntity<ApiResponse> response = restTemplate.getForEntity(paginatedUrl, ApiResponse.class);

      if (response.getBody() != null && response.getBody().getResults() != null) {
        List<Character> characters = response.getBody().getResults();

        int remainingItems = totalItemsToFetch - fetchedItems;
        int itemsToTake = Math.min(remainingItems, characters.size());

        List<Character> charactersToSave = characters.subList(0, itemsToTake);
        allCharacters.addAll(charactersToSave);
        fetchedItems += itemsToTake;

        if (response.getBody().getInfo().getNext() == null || fetchedItems >= totalItemsToFetch) {
          hasMoreItems = false;
        } else {
          currentPage++;
        }
      } else {
        hasMoreItems = false;
      }
    }

    if (!allCharacters.isEmpty()) {
      characterRepository.saveAll(allCharacters);
    }
  }

  public List<Character> getAllCharacters() {
    return characterRepository.findAll();
  }
}