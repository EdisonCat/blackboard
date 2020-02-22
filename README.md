# blackboard
Implement blackboard system RESTful APIs with Spring Boot

Language: Java

Framework: Spring Boot

Database: Amazon Web Services DynamoDB

Test: Postman

Deployment: Amazon Web Services Elastic Beanstalk

AWS EB host: http://blackboardrestfulapis-env.mmrrmpyqmp.us-west-2.elasticbeanstalk.com

---
### Structure
#### user -> APIs -> Service -> Data Access Operate -> Datasource

---
### Service: 
Bridge/Layer between DAO and APIs.
#### PersonService.java: (for dummy data)
PersonService.java is used to call PersonDao.java when APIs in PersonController.java are called.

#### ProgramService.java: (for dummy data)
ProgramService.java is used to call ProgramDao.java when APIs in ProgramController.java are called.

#### CourseService.java: (for dummy data)
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
1. Before using the APIs, you need to register for an AWS account and create 4 tables named student, professor, course, 
program respectively in DynamoDB. The primary keys of them are studentid, professorid, courseid, and programid respectively.
(all lower case)

2. Go to IAM(AWS Identity Access Management), create a group of developer and add AdministratorAccess as a policy of that 
group. Create an user and add it to the group you created. Copy the access and access secret key to local machine and 
end point url, region info. Store the keys in application.properties in resources folder in this project.

3. Enable auto-import to let your IDE download all the dependencies for you. Run the project(a Maven project).


#### Browser(Get request only currently)
1.Run the main function, which will start the server.

2.Open your browser and type in:

---
##### Course:
for course list: GET
``localhost:5000/api/blackboard/dynamodb/course``

for specific course info: GET
``localhost:5000/api/blackboard/dynamodb/course/{courseId}``

for student list of a specific course: GET
``localhost:5000/api/blackboard/dynamodb/course/{courseId}/student``

for adding professor to course(will do adding course to professor automatically): PUT
``localhost:5000/api/blackboard/dynamodb/course/{courseId}/professor/{professorId}``

for adding ta to course:
``localhost:5000/api/blackboard/dynamodb/course/{courseId}/ta/{studentId}``
  
for adding student to course(will do adding course to student automatically): PUT
``localhost:5000/api/blackboard/dynamodb/course/{courseId}/student/{studentId}``

for removing professor from course(will do removing course from professor automatically): DELETE
``localhost:5000/api/blackboard/dynamodb/course/{courseId}/professor/{professorId}``

for removing ta from course: DELETE
``localhost:5000/api/blackboard/dynamodb/course/{courseId}/ta/{studentId}``

for removing student from course(will do removing course from student automatically): DELETE
``localhost:5000/api/blackboard/dynamodb/course/{courseId}/student/{studentId}``

for deleting course from database: DELETE
``localhost:5000/api/blackboard/dynamodb/course/{courseId}``

---
##### Student:
for student list: GET
``localhost:5000/api/blackboard/dynamodb/student``
  
for specific student info: GET
``localhost:5000/api/blackboard/dynamodb/student/{studentId}``

for course list of a student: GET
``localhost:5000/api/blackboard/dynamodb/student/{studentId}/course``

for adding course to student(will do adding student to course automatically): PUT
``localhost:5000/api/blackboard/dynamodb/student/{studentId}/course/{courseId}``

for adding program to student(will do adding student to program automatically): PUT
``localhost:5000/api/blackboard/dynamodb/student/{studentId}/program/{programId}``

for removing course from student(will do removing student from course automatically): DELETE
``localhost:5000/api/blackboard/dynamodb/student/{studentId}/course/{courseId}``

for removing program from student(will do removing student from program automatically): DELETE
``localhost:5000/api/blackboard/dynamodb/student/{studentId}/program/{programId}``

for deleting student from database: DELETE
``localhost:5000/api/blackboard/dynamodb/student/{studentId}``

---
##### Professor:
for professor list: GET
``localhost:5000/api/blackboard/dynamodb/professor``

for specific professor info: GET
``localhost:5000/api/blackboard/dynamodb/professor/{professorId}``

for adding course to professor(will do adding professor to course automatically): PUT
``localhost:5000/api/blackboard/dynamodb/professor/{professorId}/course/{courseId}``

for removing course from professor(will do removing professor from course automatically): DELETE
``localhost:5000/api/blackboard/dynamodb/professor/{professorId}/course/{courseId}``

for deleting professor from database: DELETE
``localhost:5000/api/blackboard/dynamodb/professor/{professorId}``

---
##### Program
for program list: GET
``localhost:5000/api/blackboard/dynamodb/program``

for specific program info: GET
``localhost:5000/api/blackboard/dynamodb/program/{programId}``

for course list of a specific program: GET
``localhost:5000/api/blackboard/dynamodb/program/{programId}/course``

for student list of a specific program: GET
``localhost:5000/api/blackboard/dynamodb/program/{programId}/student``

for professor list of a specific program: GET
``localhost:5000/api/blackboard/dynamodb/program/{programId}/professor``
  
for adding student to program(will do adding program to student automatically): PUT
``localhost:5000/api/blackboard/dynamodb/program/{programId}/student/{studentId}``

for removing student from program(will do removing program from student automatically): DELETE
``localhost:5000/api/blackboard/dynamodb/program/{programId}/student/{studentId}``

for deleting program from database: DELETE
``localhost:5000/api/blackboard/dynamodb/program/{programId}``
  
#### Thrid-party software(Postman)
1.Open Postman.

2.Type in one of the links listed above, and choose the type of request.
