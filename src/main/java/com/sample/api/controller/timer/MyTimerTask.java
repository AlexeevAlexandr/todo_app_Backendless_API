package com.sample.api.controller.timer;

import com.sample.api.service.TodoServiceImpl;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

@Component
@Log
public class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        completeTask();
    }

    private void completeTask() {
        log.info("Attempt to delete all completed tasks");
        try {
            int count = new TodoServiceImpl().deleteCompletedTodo();
            log.info("Completed tasks have been deleted : " + count);
        } catch (Exception e){
            log.warning("Can not delete completed tasks");
        }
    }
}