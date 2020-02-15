package com.edison.blackboard.api;

import com.edison.blackboard.model.Professor;
import com.edison.blackboard.service.DynamoDbProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/blackboard/dynamodb/professor")
@RestController
public class DynamoDbProfessorController {
    private final DynamoDbProfessorService dynamoDbProfessorService;

    @Autowired
    public DynamoDbProfessorController(DynamoDbProfessorService dynamoDbProfessorService) {
        this.dynamoDbProfessorService = dynamoDbProfessorService;
    }

    @PostMapping
    public boolean insertProfessor(@RequestBody Professor professor) {
        dynamoDbProfessorService.insertProfessor(professor);
        return true;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable("id") UUID id) {
        Professor professor = dynamoDbProfessorService.getProfessorById(id);
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @GetMapping
    public List<Professor> getAllProfessors() {
        return dynamoDbProfessorService.getAllProfessors();
    }

    @PutMapping
    public void updateProfessor(@RequestBody Professor professor) {
        dynamoDbProfessorService.updateProfessor(professor);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProfessorById(@PathVariable("id") UUID id) {
        dynamoDbProfessorService.deleteProfessor(getProfessorById(id).getBody());
    }

}
