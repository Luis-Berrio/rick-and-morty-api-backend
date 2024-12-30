package com.example.rick_and_morty_api_backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rick_and_morty_api_backend.service.CharacterService;
import com.example.rick_and_morty_api_backend.models.Character;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@CrossOrigin(origins = "*") // Para React
public class CharacterController {
    
    @Autowired
    private CharacterService characterService;
    
    @PostMapping("/fetch/{count}")
    public ResponseEntity<Void> fetchCharacters(@PathVariable int count) {
        characterService.fetchAndSaveCharacters(count);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<List<Character>> getAllCharacters() {
        return ResponseEntity.ok(characterService.getAllCharacters());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        return ResponseEntity.ok(characterService.getCharacterById(id));
    }
}