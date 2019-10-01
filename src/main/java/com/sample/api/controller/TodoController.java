package com.sample.api.controller;

import com.sample.api.entity.TodoEntity;
import com.sample.api.service.TodoService;
import com.sample.api.validator.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    private final TodoService todoService;
    private final TodoValidator todoValidator;

    @Autowired
    public TodoController(TodoService todoService, TodoValidator todoValidator) {
        this.todoService = todoService;
        this.todoValidator = todoValidator;
    }

    @GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<TodoEntity> getAll(){
        return todoService.getAll();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public TodoEntity getById(@PathVariable String id){
        return todoService.getById(id);
    }

    @PostMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    public TodoEntity create(@RequestBody TodoEntity todoEntity){
        todoValidator.isValidEntity(todoEntity);
        return todoService.save(todoEntity);
    }

    @PutMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    public TodoEntity update(@RequestBody TodoEntity todoEntity){
        todoValidator.isValidEntity(todoEntity);
        return todoService.save(todoEntity);
    }

    @DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public TodoEntity markAsCompleted(@PathVariable String id){
        return todoService.markAsCompleted(id);
    }
}