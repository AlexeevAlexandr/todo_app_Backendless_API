package com.sample.api.validator;

import com.sample.api.entity.TodoEntity;
import com.sample.api.exception.TodoException;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class TodoValidator {

    public void isValidDate(TodoEntity todoEntity){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);

        try {
            format.parse(todoEntity.getDeadlineDate());
        } catch (ParseException e) {
            throw new TodoException("Invalid date format: " + e.getMessage());
        }
    }
}
