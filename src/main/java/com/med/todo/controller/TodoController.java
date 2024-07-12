package com.med.todo.controller;

import com.med.todo.dto.TodoDTO;
import com.med.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@Tag(
        name = "Todo Management REST APIs",
        description = "CRUD REST APIs for Todo Management - Add Todo, Get Todo, Get All Todos, Update Todo, Delete Todo, Complete Todo, Incomplete Todo"
)
public class TodoController {


    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Build Add Todo REST API
    @Operation(
            summary = "Add Todo REST API",
            description = "Add Todo REST API to save a new todo into the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS 201 CREATED"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDTO){
       TodoDTO todoDTO1= todoService.addTodo(todoDTO);
        return new ResponseEntity<>(todoDTO1, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get Todo REST API",
            description = "Get Todo REST API to retrieve a todo by its ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/get/{id}")
    public ResponseEntity<TodoDTO> getTodo(@PathVariable Long id){
        TodoDTO result= todoService.getTodo(id);
       return  new ResponseEntity<>(result,HttpStatus.OK);
    }

    @Operation(
            summary = "Get All Todos REST API",
            description = "Get All Todos REST API to retrieve all todos"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("allTodos")
    public ResponseEntity<List<TodoDTO>> getAllTodos(){
       List<TodoDTO> list=todoService.getAllTodos();
       return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @Operation(
            summary = "Update Todo REST API",
            description = "Update Todo REST API to update an existing todo"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@RequestBody TodoDTO todoDTO, @PathVariable Long id){
      TodoDTO dto=  todoService.updateTodo(todoDTO,id);
      return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    @Operation(
            summary = "Delete Todo REST API",
            description = "Delete Todo REST API to delete a todo by its ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Task deleted successfully");

    }



    @Operation(
            summary = "Complete Todo REST API",
            description = "Complete Todo REST API to mark a todo as completed"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PatchMapping("complete/{id}")
    public  ResponseEntity<TodoDTO> completeTodo(@PathVariable("id") Long todoId){
        TodoDTO updatedTodo= todoService.completeTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
}



    @Operation(
            summary = "Incomplete Todo REST API",
            description = "Incomplete Todo REST API to mark a todo as incomplete"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PatchMapping("incomplete/{id}")
    public  ResponseEntity<TodoDTO> incompleteTodo(@PathVariable("id") Long todoId){
        TodoDTO updatedTodo= todoService.incompleteTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }
}
