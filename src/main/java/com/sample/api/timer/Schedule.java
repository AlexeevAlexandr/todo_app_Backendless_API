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
        deleteAllCompletedTasks();
    }

    private void deleteAllCompletedTasks() {
        log.info("Attempt to delete all completed tasks");
        int count = todoService.deleteCompletedTodo();
        log.info("Completed tasks have been deleted : " + count);

    }
}