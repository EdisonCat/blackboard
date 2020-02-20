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

    @GetMapping(path = "{professorId}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable("professorId") UUID professorId) {
        Professor professor = dynamoDbProfessorService.getProfessorById(professorId);
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

    @PutMapping(path = "{professorId}/course/{courseId}")
    public boolean addCourse(@PathVariable("professorId") UUID professorId, @PathVariable("courseId") UUID courseId) {
        return dynamoDbProfessorService.addCourse(professorId, courseId);
    }

    @PutMapping(path = "{professorId}/program/{programId}")
    public boolean addProgram(@PathVariable("professorId") UUID professorId, @PathVariable("programId") UUID programId) {
        return dynamoDbProfessorService.addProgram(professorId, programId);
    }

    @DeleteMapping(path = "{professorId}/program/{programId}")
    public boolean removeProgram(@PathVariable("professorId") UUID professorId, @PathVariable("programId") UUID programId) {
        return dynamoDbProfessorService.removeProgram(professorId, programId);
    }

    @DeleteMapping(path = "{professorId}/course/{courseId}")
    public boolean removeCourse(@PathVariable("professorId") UUID professorId, @PathVariable("courseId") UUID courseId) {
        return dynamoDbProfessorService.removeCourse(professorId, courseId);
    }

    @DeleteMapping(path = "{professorId}")
    public void deleteProfessorById(@PathVariable("professorId") UUID professorId) {
        dynamoDbProfessorService.deleteProfessor(getProfessorById(professorId).getBody());
    }

}
