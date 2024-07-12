package com.med.todo.service.impl;

import com.med.todo.dto.TodoDTO;
import com.med.todo.entity.Todo;
import com.med.todo.exception.ResourceNotFoundException;
import com.med.todo.repository.TodoRepository;
import com.med.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public TodoDTO addTodo(TodoDTO todoDTO) {

//        Todo todo=new Todo();
//        todo.setTitle(todoDTO.title());
//        todo.setDescription(todoDTO.description());
//        todo.setComplete(todoDTO.completed());
        Todo todo= modelMapper.map(todoDTO,Todo.class);

       Todo savedEntity= todoRepository.save(todo);

       TodoDTO savedTodoDTO = modelMapper.map(savedEntity,TodoDTO.class);
// converting savedEntity to DTO

//        TodoDTO savedTodoDTO= new TodoDTO(
//                savedEntity.getId(),
//                savedEntity.getTitle(),
//                savedEntity.getDescription(),
//                savedEntity.isComplete()
//        );

        return savedTodoDTO;


    }

    @Override
    public TodoDTO getTodo(Long id) {

        try{
            Todo todoEntity= todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Task doesn't exist"));
            // Inefficient statement- Todo todoEntity= todoRepository.findById(id).get();
            TodoDTO result= modelMapper.map(todoEntity,TodoDTO.class);
            return result;

        }
        catch (ResourceNotFoundException e){
            throw e;
        }


    }

    @Override
    public List<TodoDTO> getAllTodos() {
     List<Todo> todoList=  todoRepository.findAll();
    return todoList.stream()
             .map(x->modelMapper.map(x, TodoDTO.class))
             .toList();

    }

    @Override
    public TodoDTO updateTodo(TodoDTO todoDTO, Long id) {

        Todo todoEntity= todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Id"+id+"doesn't exist"));
        todoEntity.setTitle(todoDTO.getTitle());
        todoEntity.setDescription(todoDTO.getDescription());
        todoEntity.setCompleted(todoDTO.isCompleted());
       Todo savedEntity=  todoRepository.save(todoEntity);
        return modelMapper.map(savedEntity,TodoDTO.class);
    }

    @Override
    public void deleteTodo(Long id) {

        Todo todoToDelete= todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Ic"+id+"doesn't exist"));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDTO completeTodo(Long id) {
     Todo todoEntity=   todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Given id"+id+"doesn't exist"));
     todoEntity.setCompleted(Boolean.TRUE);
     Todo updatedToto= todoRepository.save(todoEntity);
     return modelMapper.map(updatedToto,TodoDTO.class);

    }

    @Override
    public TodoDTO incompleteTodo(Long id) {
        Todo todoEntity=   todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Given id"+id+"doesn't exist"));
        todoEntity.setCompleted(Boolean.FALSE);
        Todo updatedToto= todoRepository.save(todoEntity);
        return modelMapper.map(updatedToto,TodoDTO.class);

    }
}
