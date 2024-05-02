package com.projects.moviebookingapp.model.entity;

import com.projects.moviebookingapp.model.enums.AvailableTechnology;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "theatres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "location")
    private String location;

    @Column(name = "available_technology")
    private AvailableTechnology availableTechnology;
}
