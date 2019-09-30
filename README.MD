**TODO_app**

This program to create tasks (TODO), implemented as a REST service.

Commands that can be executed (for example if you use localhost):

method GET:

    http://localhost:8080/todo - get all tasks;
    http://localhost:8080/todo/`id` - get task by id;
    
method POST:

    http://localhost:8080/todo - to create a new task, data is sent as a body;
    
    The user must transfer the data in json format, for example:
    {
      "description":"here will be a description",
      "owner":"Owner's Name",
      "deadlineDate":"2020-01-01"
    }
    
method PUT:

    http://localhost:8080/todo - to update a task, data is sent as a body;
    
    The user must transfer the data in json format, for example:
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

Instructions how to install:

    for instalation the database have to be cleared
