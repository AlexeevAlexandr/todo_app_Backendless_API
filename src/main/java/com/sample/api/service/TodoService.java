package com.sample.api.service;

import com.sample.api.entity.TodoEntity;

import java.util.List;

public interface TodoService {

    TodoEntity save(TodoEntity todoEntity);

    TodoEntity getById(String id);

    List<TodoEntity> getAll();

    TodoEntity markAsCompleted(String id);
}