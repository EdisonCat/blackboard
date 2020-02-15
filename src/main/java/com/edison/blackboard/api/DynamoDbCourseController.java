package com.edison.blackboard.api;

import com.edison.blackboard.model.Course;
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

    @GetMapping(path = "{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") UUID id) {
        Course course = dynamoDbCourseService.getCourseById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return dynamoDbCourseService.getAllCourses();
    }

    @PutMapping
    public void updateCourse(@RequestBody Course course) {
        dynamoDbCourseService.updateCourse(course);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCourseById(@PathVariable("id") UUID id) {
        dynamoDbCourseService.deleteCourse(getCourseById(id).getBody());
    }

}
