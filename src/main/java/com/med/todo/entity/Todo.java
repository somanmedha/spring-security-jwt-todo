package com.med.todo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // JPA assumes column names as field names if name attribute isn't defined explicitly
    @Column(name="title", nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)


    private boolean completed;



}
