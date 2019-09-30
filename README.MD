# **TODO_app**

This program to create tasks (TODO), implemented as a REST service.

**Commands that can be executed** (for example if you use localhost):

method GET:

    http://localhost:8080/todo - get all tasks;
    http://localhost:8080/todo/`id` - get task by id;
    
method POST:

    http://localhost:8080/todo - to create a new task, data is sent as a body;
    
    the data must be in json format, for example:
    {
      "description":"here will be a description",
      "owner":"Owner's Name",
      "deadlineDate":"2020-01-01"
    }
    
method PUT:

    http://localhost:8080/todo - to update a task, data is sent as a body;
    
    the data must be in json format, for example:
        {
          "objectId":"0123456789",
          "description":"here will be a description",
          "owner":"Owner's Name",
          "deadlineDate":"2020-01-01"
        }
            
    "description", "owner" or "deadlineDate" - these data is optional, 
    you can change what you whant, for example:
    
    {
      "objectId":"0123456789",
      "description":"here will be a description",
    }
    
method DELETE:

    http://localhost:8080/todo/`id` - it's used to mark the task as completed;
    
    
All tasks that were marked as completed - will be automatically cleaned once a day at 6:00 a.m.

**Instructions how to use:**

   1. [https://develop.backendless.com][https://develop.backendless.com] - 
        register on the site;
   2. [https://backendless.com/docs/android/users_overview.html][https://backendless.com/docs/android/users_overview.html] -
        create your app using this reference;
   3. insert your applicationId and apiKey to connection.properties;
   
   4. command to installing: _mvn package_
      
   5. command to launching: _java -jar target/todo_app-1.0-SNAPSHOT.jar_



[https://backendless.com/docs/android/users_overview.html]: https://backendless.com/docs/android/users_overview.html

[https://develop.backendless.com]: https://develop.backendless.com