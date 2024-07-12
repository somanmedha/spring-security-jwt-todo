package com.med.todo.service;

import com.med.todo.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    TodoDTO addTodo(TodoDTO todoDTO);
    TodoDTO getTodo(Long id);
    List<TodoDTO> getAllTodos();
    TodoDTO updateTodo(TodoDTO todoDTO,Long id);
    void deleteTodo(Long id);
    TodoDTO completeTodo(Long id);
    TodoDTO incompleteTodo(Long id);
}
