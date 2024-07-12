package com.med.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class  TodoDTO{
  private Long id;
  private String title;
  private String description;

  private boolean completed;

public TodoDTO(){

}

}

