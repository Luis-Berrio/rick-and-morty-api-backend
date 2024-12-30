package com.example.rick_and_morty_api_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rick_and_morty_api_backend.models.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {}
