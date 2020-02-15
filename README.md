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

#### DynamoDbCourseService.java: (for real data in DynamoDB)
Can be called by DynamoDbCourseController to call DAO to operate course datasource in DynamoDB.

#### DynamoDbProgramService.java: (for real data in DynamoDB)
Can be called by DynamoDbCourseController to call DAO to operate program datasource in DynamoDB.

#### DynamoDbStudentService.java: (for real data in DynamoDB)
Can be called by DynamoDbStudentController to call DAO to operate student datasource in DynamoDB.

#### DynamoDbProfessorService.java: (for real data in DynamoDB)
Can be called by DynamoDbProfessorController to call DAO to operate professor datasource in DynamoDB.

---
### DAO(Data Access Operation):
The layer that is close to datasource.  
#### PersonDao.java: (for dummy data)
PersonDao.java is used to access person(student/professor) datasource and operate, this class can be called by PersonService.class.

#### ProgramDao.java: (for dummy data)
ProgramDao.java is used to access program datasource and operate, this class can be called by ProgramService.class.

#### CourseDao.java: (for dummy data)
CourseDao.java is used to access course datasource and operate, this class can be called by CourseService.class.

#### DynamoDbConfig.java: (for real data in DynamoDB)
DynamoDbConfig.java is the configuration for using AWS DynamoDB, which specifies the keys to access AWS DynamoDB.

---
### API(Controllers): 
The layer the users can touch.
#### PersonController.java: (for dummy data)
PersonController.java is the set of APIs that can be used to call PersonService.java to call PersonDao to operate the datasource.

#### ProgramController.java: (for dummy data)
ProgramController.java is the set of APIs that can be used to call ProgramService.java to call ProgramDao to operate program datasource.

#### CourseController.java: (for dummy data)
CourseController.java is the set of APIs that can be used to call CourseService.java to call CourseDao to operate course datasource.

#### DynamoDbCourseController.java: (for real data in DynamoDB)
The set of APIs that can be used by users directly to call service and then dao/config to operate course datasource in DynamoDB.

#### DynamoDbProgramController.java: (for real data in DynamoDB)
The set of APIs that can be used by users directly to call service and then dao/config to operate program datasource in DynamoDB.

#### DynamoDbStudentController.java: (for real data in DynamoDB)
The set of APIs that can be used by users directly to call service and then dao/config to operate student datasource in DynamoDB.

#### DynamoDbProfessorController.java: (for real data in DynamoDB)
The set of APIs that can be used by users directly to call service and then dao/config to operate professor datasource in DynamoDB.

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
  
  for course list:
  
  ```localhost:8080/api/blackboard/dynamodb/course```

#### Thrid-party software(Postman)
1.Open Postman.

2.Type in one of the links above, and choose the type of request.
