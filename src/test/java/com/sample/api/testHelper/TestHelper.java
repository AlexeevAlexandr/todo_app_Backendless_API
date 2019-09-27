package com.sample.api.testHelper;

import com.backendless.Backendless;
import com.sample.api.entity.TodoEntity;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class TestHelper {

    public JSONObject getJsonObjectFromFile(String filePath) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(new FileReader(getFileFromResources(filePath)));
    }

    private File getFileFromResources(String path) {
        return new File(Objects.requireNonNull(getClass().getClassLoader().getResource(path)).getFile());
    }

    public static List<TodoEntity> getAll() {
        return Backendless.Data.of(TodoEntity.class).find();
    }


    private static TodoEntity getById(String id) {
        return Backendless.Data.of(TodoEntity.class).findById(id);
    }

    public static void delete(String id) {
        TodoEntity todoEntity = getById(id);
        Backendless.Data.of(TodoEntity.class).remove(todoEntity);
    }
}
