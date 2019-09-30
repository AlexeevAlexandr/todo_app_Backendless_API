package com.sample.api.timer;

import com.sample.api.service.TodoService;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log
public class Schedule {

    final private TodoService todoService;

    public Schedule(TodoService todoService) {
        this.todoService = todoService;
    }

    // Scheduled at 6:00 a.m. every day
    @Scheduled(cron = "0 0 6 ? * *")
    public void run() {
        scheduledTask();
    }

    private void scheduledTask() {
        log.info("Attempt to delete all completed tasks");
        try {
            int count = todoService.deleteCompletedTodo();
            log.info("Completed tasks have been deleted : " + count);
        } catch (Exception e){
            log.warning("Can not delete completed tasks");
        }
    }
}