package com.sample.api.service;

import com.backendless.Backendless;
import com.backendless.servercode.IBackendlessService;
import com.sample.api.entity.TodoEntity;
import com.sample.api.exception.TodoException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class TodoServiceImpl implements IBackendlessService, TodoService {

    @Override
    public TodoEntity save(TodoEntity todoEntity) {
        TodoEntity entity;
        try {
            log.info("Attempt to save entity");
            entity =  Backendless.Data.of(TodoEntity.class).save(todoEntity);
            log.info("Entity saved successfully: " + entity.toString());
        } catch (Exception e){
            log.warning("Can not save entity\n" + e.getMessage());
            throw new TodoException(e.getMessage());
        }
        return entity;
    }

    @Override
    public TodoEntity getById(String id) {
        TodoEntity todoEntity;
        try {
            log.info("Getting entity by id: " + id);
            todoEntity = Backendless.Data.of(TodoEntity.class).findById(id);
            log.info("Found entity: " + todoEntity.toString());
        } catch (Exception e){
            log.info(String.format("Id '%s' not found", id));
            throw new TodoException(e.getMessage());
        }
        return todoEntity;
    }

    @Override
    public List<TodoEntity> getAll() {
        List<TodoEntity> todoEntities;
        try {
            log.info("Getting all entities");
            todoEntities = Backendless.Data.of(TodoEntity.class).find();
            log.info("Getting all entities successfully");
        } catch (Exception e){
            log.warning("Can not get all entities: " + e.getMessage());
            throw new TodoException(e.getMessage());
        }
        return todoEntities;
    }

    @Override
    public TodoEntity markAsCompleted(String id) {
        log.info("Attempt to mark entity as completed");
        TodoEntity todoEntity = getById(id);
        todoEntity.setCompleted(true);
        TodoEntity entity;
        try {
            entity = Backendless.Data.of(TodoEntity.class).save(todoEntity);
            log.info("Entity marked as completed");
        } catch (Exception e) {
            log.warning("Can not mark entity as completed: " + e.getMessage());
            throw new TodoException(e.getMessage());
        }
        return entity;
    }

    @Override
    public int deleteCompletedTodo() {
        log.info("Attempt to remove completed entities");
        int count = 0;
        List<TodoEntity> entityList = getAll();
        for (TodoEntity entity : entityList) {
            if (entity.isCompleted()){
                count++;
                try {
                    log.info("Attempt to remove entity: " + entity.toString());
                    Backendless.Data.of(TodoEntity.class).remove(entity);
                    log.info("Entity removed");
                } catch (Exception e) {
                    log.warning("Can not remove entity: " + e.getMessage());
                    throw new TodoException(e.getMessage());
                }
            }
        }
        return count;
    }
}