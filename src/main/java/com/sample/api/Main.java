package com.sample.api;

import com.sample.api.controller.timer.MyTimerTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new MyTimerTask(), 0, 30*1000);
    }
}