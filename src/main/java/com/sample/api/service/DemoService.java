
package com.sample.api.service;

import com.backendless.Backendless;
import com.backendless.servercode.IBackendlessService;
import com.sample.api.entity.TodoEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class DemoService implements IBackendlessService {

  static{
    Properties properties = new Properties();
    try (InputStream input = DemoService.class.getClassLoader().getResourceAsStream("connection.properties")){
      properties.load(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
    String applicationId = properties.getProperty("applicationId");
    String apiKey = properties.getProperty("apiKey");
    Backendless.initApp(applicationId, apiKey);
  }

  private TodoEntity save(TodoEntity todoEntity) {
    return Backendless.Data.of(TodoEntity.class).save(todoEntity);
  }

  private TodoEntity getById(String id) {
    return Backendless.Data.of(TodoEntity.class).findById(id);
  }

  private List<TodoEntity> getAll() {
    return Backendless.Data.of(TodoEntity.class).find();
  }


  public static void main(String[] args) {
    DemoService demoService = new DemoService();
//    TodoEntity todoEntity = new TodoEntity();
//    todoEntity.setDeadlineDate("2020-12-31");
//    todoEntity.setDescription("here will be a description");
//    todoEntity.setOwner("Owner's Name");
//    System.out.println(demoService.save(todoEntity));

//    System.out.println(demoService.getAll().size());

    TodoEntity todoEntity = demoService.getById("59DBBBF6-057D-4D82-FFC2-3A359F35D000");
    todoEntity.setDescription("rrrrrrrrr");
    System.out.println(demoService.save(todoEntity));
  }
}
                           