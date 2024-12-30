package com.example.rick_and_morty_api_backend.models;

import lombok.Data;

@Data
public class Info {
    private int count;
    private int pages;
    private String next;
    private String prev;
}