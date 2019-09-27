package com.sample.api.controller.timer;

import com.sample.api.service.TodoService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.TimerTask;

@Component
@Log
public class MyTimerTask extends TimerTask {

    private final TodoService todoService;

    public MyTimerTask(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public void run() {
        System.out.println("Timer task started at:"+new Date());
        completeTask();
        System.out.println("Timer task finished at:"+new Date());
    }

    private void completeTask() {
        log.info("Attempt to delete all completed tasks.");
        try {
            todoService.deleteCompletedTodo();
        } catch (Exception e){
        }
    }
}