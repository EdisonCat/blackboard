package com.edison.blackboard.api;

import com.edison.blackboard.model.Course;
import com.edison.blackboard.model.Student;
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

    @Autowired
    public DynamoDbStudentController(DynamoDbStudentService dynamoDbStudentService) {
        this.dynamoDbStudentService = dynamoDbStudentService;
    }

    @PostMapping
    public boolean insertStudent(@RequestBody Student student) {
        dynamoDbStudentService.insertStudent(student);
        return true;
    }

    @GetMapping(path = "{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") UUID studentId) {
        Student student = dynamoDbStudentService.getStudentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return dynamoDbStudentService.getAllStudents();
    }

    @GetMapping(path = "{studentId}/course")
    public List<Course> getAllCourses(@PathVariable("studentId") UUID studentId) {
        return dynamoDbStudentService.getAllCourses(studentId);
    }

    @PutMapping(path = "{studentId}/course/{courseId}")
    public boolean addCourse(@PathVariable("studentId") UUID studentId,
                             @PathVariable("courseId") UUID courseId) {
        return dynamoDbStudentService.addCourse(studentId, courseId);
    }

    @PutMapping
    public void updateStudent(@RequestBody Student student) {
        dynamoDbStudentService.updateStudent(student);
    }

    @PutMapping(path = "{studentId}/program/{programId}")
    public boolean addProgram(@PathVariable("studentId") UUID studentId,
                              @PathVariable("programId") UUID programId) {
        return dynamoDbStudentService.addProgram(studentId, programId);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudentById(@PathVariable("studentId") UUID studentId) {
        dynamoDbStudentService.deleteStudent(getStudentById(studentId).getBody());
    }

    @DeleteMapping(path = "{studentId}/course/{courseId}")
    public boolean removeCourse(@PathVariable("studentId") UUID studentId,
                                @PathVariable("courseId") UUID courseId) {
        return dynamoDbStudentService.removeCourse(studentId, courseId);
    }

    @DeleteMapping(path = "{studentId}/program/{programId}")
    public boolean removeProgram(@PathVariable("studentId") UUID studentId,
                                 @PathVariable("programId") UUID programId) {
        return dynamoDbStudentService.removeProgram(studentId, programId);
    }

}
