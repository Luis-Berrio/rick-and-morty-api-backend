package com.example.rick_and_morty_api_backend.models;
import lombok.Data;
import java.util.List;

@Data
public class ApiResponse {
    private Info info;
    private List<Character> results;
}

