# blackboard
Implement blackboard system with Spring Boot

---
### Structure
#### user -> APIs -> Service -> Data Access Operate -> Datasource

---
### Service: 
Bridge/Layer between DAO and APIs.
#### PersonService.java:
PersonService.java is used to call PersonDao.java when APIs in PersonController.java are called.

#### ProgramService.java:
ProgramService.java is used to call ProgramDao.java when APIs in ProgramController.java are called.

#### CourseService.java:
CourseService.java is used to call CourseDao.java when APIs in CourseController.java are called.

---

### Config
Configuration for database.(DynamoDB in this project)
#### DynamoDbConfig.java
DynamoDbConfig.java is the configuration for using AWS DynamoDB, which specifies the keys to access AWS DynamoDB.

---
### DAO(Data Access Operation):
The layer that is close to datasource.  
#### PersonDao.java
PersonDao.java is used to access person(student/professor) datasource and operate, this class can be called by PersonService.class.

#### ProgramDao.java
ProgramDao.java is used to access program datasource and operate, this class can be called by ProgramService.class.

#### CourseDao.java
CourseDao.java is used to access course datasource and operate, this class can be called by CourseService.class.


---
### API(Controllers): 
The layer the users can touch.
#### PersonController.java:
PersonController.java is the set of APIs that can be used to call PersonService.java to call PersonDao to operate the datasource.

#### ProgramController.java:
ProgramController.java is the set of APIs that can be used to call ProgramService.java to call ProgramDao to operate program datasource.

#### CourseController.java:
CourseController.java is the set of APIs that can be used to call CourseService.java to call CourseDao to operate course datasource.

---
### Usage
#### Browser(Get request only currently)
1.Run the main function, which will start the server.

2.Open your browser and type in:
  
  for student list:
   
  ```localhost:8080/api/blackboard/dynamodb/student```
  
  for professor list: 
  
  ```localhost:8080/api/blackboard/dynamodb/professor```
  
  for program list: 
  
  ```localhost:8080/api/blackboard/dynamodb/program```

#### Thrid-party software(Postman)
1.Open Postman.

2.Type in one of the links above, and choose the type of request.
