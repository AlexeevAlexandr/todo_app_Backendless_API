package com.sample.api;

import com.backendless.Backendless;
import com.sample.api.service.TodoServiceImpl;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
@Log
public class Main {

    @PostConstruct
    public void init(){
        Properties properties = new Properties();
        try (InputStream input = TodoServiceImpl.class.getClassLoader().getResourceAsStream("connection.properties")) {
            log.info("Attempt to load properties");
            properties.load(input);
            log.info("Properties loaded");
        } catch (IOException e) {
            log.warning("Can not load properties \n" + e.getMessage());
        }
        String applicationId = properties.getProperty("applicationId");
        String apiKey = properties.getProperty("apiKey");
        Backendless.initApp(applicationId, apiKey);
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}