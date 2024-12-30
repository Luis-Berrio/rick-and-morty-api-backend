package com.example.rick_and_morty_api_backend.models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "characters")
public class Character {
    @Id
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "origin_name")),
        @AttributeOverride(name = "url", column = @Column(name = "origin_url"))
    })
    private Origin origin;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "location_name")),
        @AttributeOverride(name = "url", column = @Column(name = "location_url"))
    })
    private Location location;
    
    private String image;
    private String url;
    private String created;
}

@Embeddable
@Data
class Origin {
    private String name;
    private String url;
}

@Embeddable
@Data
class Location {
    private String name;
    private String url;
}