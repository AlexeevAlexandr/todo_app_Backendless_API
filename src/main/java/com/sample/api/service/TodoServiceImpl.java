package com.sample.api.service;

import com.backendless.Backendless;
import com.backendless.servercode.IBackendlessService;
import com.sample.api.entity.TodoEntity;
import com.sample.api.exception.TodoException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@Service
@Log
public class TodoServiceImpl implements IBackendlessService, TodoService {

    static{
        Properties properties = new Properties();
        try (InputStream input = TodoServiceImpl.class.getClassLoader().getResourceAsStream("connection.properties")){
          properties.load(input);
        } catch (IOException e) {
          e.printStackTrace();
        }
        String applicationId = properties.getProperty("applicationId");
        String apiKey = properties.getProperty("apiKey");
        Backendless.initApp(applicationId, apiKey);
    }

    @Override
    public TodoEntity save(TodoEntity todoEntity) {
        log.info("Attempt to save entity");
        TodoEntity entity =  Backendless.Data.of(TodoEntity.class).save(todoEntity);
        log.info("Entity saved successfully: " + entity.toString());
        return entity;
    }

    @Override
    public TodoEntity getById(String id) {
        TodoEntity todoEntity;
        try {
            log.info("Getting a task by id: " + id);
            todoEntity = Backendless.Data.of(TodoEntity.class).findById(id);
        } catch (Exception e){
            throw new TodoException(e.getMessage());
        }
        return todoEntity;
    }

    @Override
    public List<TodoEntity> getAll() {
        log.info("Getting all tasks");
        return Backendless.Data.of(TodoEntity.class).find();
    }

    @Override
    public TodoEntity markAsCompleted(String id) {
        TodoEntity todoEntity = getById(id);
        todoEntity.setCompleted(true);
        log.info("Mark a task as completed");
        return Backendless.Data.of(TodoEntity.class).save(todoEntity);
    }

    @Override
    public int deleteCompletedTodo() {
        int count = 0;
        List<TodoEntity> entityList = getAll();
        for (TodoEntity entity : entityList) {
            if (entity.isCompleted()){
                count++;
                log.info("Attempt to remove entity: " + entity.toString());
                Backendless.Data.of(TodoEntity.class).remove(entity);
                log.info("Entity removed");
            }
        }
        return count;
    }
}