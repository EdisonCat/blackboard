package com.edison.blackboard.api;

import com.edison.blackboard.model.Course;
import com.edison.blackboard.model.Student;
import com.edison.blackboard.service.DynamoDbCourseService;
import com.edison.blackboard.service.DynamoDbProgramService;
import com.edison.blackboard.service.DynamoDbStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/blackboard/dynamodb/student")
@RestController
public class DynamoDbStudentController {
    private final DynamoDbStudentService dynamoDbStudentService;
    private final DynamoDbCourseService dynamoDbCourseService;
    private final DynamoDbProgramService dynamoDbProgramService;

    @Autowired
    public DynamoDbStudentController(DynamoDbStudentService dynamoDbStudentService,
                                     DynamoDbCourseService dynamoDbCourseService,
                                     DynamoDbProgramService dynamoDbProgramService) {
        this.dynamoDbStudentService = dynamoDbStudentService;
        this.dynamoDbCourseService = dynamoDbCourseService;
        this.dynamoDbProgramService = dynamoDbProgramService;
    }

    @PostMapping
    public boolean insertStudent(@RequestBody Student student) {
        dynamoDbStudentService.insertStudent(student);
        return true;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") UUID id) {
        Student student = dynamoDbStudentService.getStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return dynamoDbStudentService.getAllStudents();
    }

    @GetMapping(path = "{studentId}/course")
    public List<Course> getAllCourses(@PathVariable("studentId") UUID studentId) {
        return dynamoDbStudentService.getStudentById(studentId).getCourseList();
    }

    @PutMapping(path = "{studentId}/course/{courseId}")
    public boolean addCourse(@PathVariable("studentId") UUID studentId,
                             @PathVariable("courseId") UUID courseId) {
        dynamoDbStudentService.updateStudent(dynamoDbStudentService.getStudentById(studentId)
                .addCourse(dynamoDbCourseService.getCourseById(courseId)));
        dynamoDbCourseService.updateCourse(dynamoDbCourseService.getCourseById(courseId)
                .addStudent(dynamoDbStudentService.getStudentById(studentId)));
        return true;
    }

    @PutMapping
    public void updateStudent(@RequestBody Student student) {
        dynamoDbStudentService.updateStudent(student);
    }

    @PutMapping(path = "{studentId}/program/{programId}")
    public boolean addProgram(@PathVariable("studentId") UUID studentId, @PathVariable("programId") UUID programId) {
        dynamoDbStudentService.updateStudent(dynamoDbStudentService.getStudentById(studentId)
                .setProgram(dynamoDbProgramService.getProgramById(programId).getName()));
        dynamoDbProgramService.updateProgram(dynamoDbProgramService.getProgramById(programId)
                .addStudent(dynamoDbStudentService.getStudentById(studentId)));
        return true;
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudentById(@PathVariable("id") UUID id) {
        dynamoDbStudentService.deleteStudent(getStudentById(id).getBody());
    }

    @DeleteMapping(path = "{studentId}/course/{courseId}")
    public boolean removeCourse(@PathVariable("studentId") UUID studentId, @PathVariable("courseId") UUID courseId) {
        dynamoDbStudentService.updateStudent(dynamoDbStudentService.getStudentById(studentId)
                .removeCourse(dynamoDbCourseService.getCourseById(courseId)));
        dynamoDbCourseService.updateCourse(dynamoDbCourseService.getCourseById(courseId)
                .removeStudent(dynamoDbStudentService.getStudentById(studentId)));
        return true;
    }

    @DeleteMapping(path = "{studentId}/program/{programId}")
    public boolean removeProgram(@PathVariable("studentId") UUID studentId, @PathVariable("programId") UUID programId) {
        dynamoDbStudentService.updateStudent(dynamoDbStudentService.getStudentById(studentId)
                .removeProgram(dynamoDbProgramService.getProgramById(programId)));
        dynamoDbProgramService.updateProgram(dynamoDbProgramService.getProgramById(programId)
                .removeStudent(dynamoDbStudentService.getStudentById(studentId)));
        return true;
    }

}
