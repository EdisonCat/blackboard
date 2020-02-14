package com.edison.blackboard.api;

import com.edison.blackboard.model.Student;
import com.edison.blackboard.service.DynamoDbStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") UUID id) {
        Student student = dynamoDbStudentService.getStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping
    public void updateStudent(@RequestBody Student student) {
        dynamoDbStudentService.updateStudent(student);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudentById(@PathVariable("id") UUID id) {
        dynamoDbStudentService.deleteStudent(getStudentById(id).getBody());
    }

}
