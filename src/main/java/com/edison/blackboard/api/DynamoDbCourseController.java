package com.edison.blackboard.api;

import com.edison.blackboard.model.Course;
import com.edison.blackboard.model.Student;
import com.edison.blackboard.service.DynamoDbCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/blackboard/dynamodb/course")
@RestController
public class DynamoDbCourseController {
    private final DynamoDbCourseService dynamoDbCourseService;

    @Autowired
    public DynamoDbCourseController(DynamoDbCourseService dynamoDbCourseService) {
        this.dynamoDbCourseService = dynamoDbCourseService;
    }

    @PostMapping
    public boolean insertCourse(@RequestBody Course course) {
        dynamoDbCourseService.insertCourse(course);
        return true;
    }

    @GetMapping(path = "{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable("courseId") UUID courseId) {
        Course course = dynamoDbCourseService.getCourseById(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return dynamoDbCourseService.getAllCourses();
    }

    @GetMapping(path = "{courseId}/student")
    public List<Student> getAllStudents(@PathVariable("courseId") UUID courseId) {
        return dynamoDbCourseService.getAllStudents(courseId);
    }

    @PutMapping
    public void updateCourse(@RequestBody Course course) {
        dynamoDbCourseService.updateCourse(course);
    }

    @PutMapping(path = "{courseId}/professor/{professorId}")
    public boolean addProfessor(@PathVariable("professorId") UUID professorId,
                                @PathVariable("courseId") UUID courseId) {
        return dynamoDbCourseService.addProfessor(courseId, professorId);
    }

    @PutMapping(path = "{courseId}/ta/{studentId}")
    public boolean addTa(@PathVariable("courseId") UUID courseId, @PathVariable("studentId") UUID studentId) {
        return dynamoDbCourseService.addTa(courseId, studentId);
    }

    @PutMapping(path = "{courseId}/student/{studentId}")
    public boolean addStudent(@PathVariable("courseId") UUID courseId, @PathVariable("studentId") UUID studentId) {
        return dynamoDbCourseService.addStudent(courseId, studentId);
    }

    @DeleteMapping(path = "{courseId}/student/{studentId}")
    public boolean removeStudent(@PathVariable("courseId") UUID courseId, @PathVariable("studentId") UUID studentId) {
        return dynamoDbCourseService.removeStudent(courseId, studentId);
    }

    @DeleteMapping(path = "{courseId}")
    public void deleteCourseById(@PathVariable("courseId") UUID courseId) {
        dynamoDbCourseService.deleteCourse(getCourseById(courseId).getBody());
    }

    @DeleteMapping(path = "{courseId}/professor/{professorId}")
    public boolean removeProfessor(@PathVariable("professorId") UUID professorId,
                                   @PathVariable("courseId") UUID courseId) {
        return dynamoDbCourseService.removeProfessor(courseId, professorId);
    }

    @DeleteMapping(path = "{courseId}/ta/{studentId}")
    public boolean removeTa(@PathVariable("courseId") UUID courseId, @PathVariable("studentId") UUID studentId) {
        return dynamoDbCourseService.removeTa(courseId, studentId);
    }
}
