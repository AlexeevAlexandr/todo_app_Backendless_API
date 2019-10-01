package com.sample.api.validator;

import com.sample.api.entity.TodoEntity;
import com.sample.api.exception.TodoException;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class TodoValidatorImpl implements TodoValidator {

    @Override
    public void isValidEntity(TodoEntity todoEntity) {
        String deadlineDate = todoEntity.getDeadlineDate();
        String description = todoEntity.getDescription();
        String owner = todoEntity.getOwner();

        if (description == null || description.isEmpty()) {
            throw new TodoException("Date can not be empty or null");
        }

        if (deadlineDate == null || deadlineDate.isEmpty()) {
            throw new TodoException("Description can not be empty or null");
        }

        if (owner == null || owner.isEmpty()) {
            throw new TodoException("Owner can not be empty or null");
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);

        try {
            format.parse(todoEntity.getDeadlineDate());
        } catch (ParseException e) {
            throw new TodoException("Invalid date format: " + e.getMessage());
        }
    }
}
