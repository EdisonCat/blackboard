package com.edison.blackboard.api;

import com.edison.blackboard.model.Program;
import com.edison.blackboard.service.DynamoDbProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/blackboard/dynamodb/program")
@RestController
public class DynamoDbProgramController {
    private final DynamoDbProgramService dynamoDbProgramService;

    @Autowired
    public DynamoDbProgramController(DynamoDbProgramService dynamoDbProgramService) {
        this.dynamoDbProgramService = dynamoDbProgramService;
    }

    @PostMapping
    public boolean insertProgram(@RequestBody Program program) {
        dynamoDbProgramService.insertProgram(program);
        return true;
    }

    @GetMapping(path = "{programId}")
    public ResponseEntity<Program> getProgramById(@PathVariable("programId") UUID programId) {
        Program program = dynamoDbProgramService.getProgramById(programId);
        return new ResponseEntity<>(program, HttpStatus.OK);
    }

    @GetMapping
    public List<Program> getAllPrograms() {
        return dynamoDbProgramService.getAllPrograms();
    }

    @PutMapping
    public void updateProgram(@RequestBody Program program) {
        dynamoDbProgramService.updateProgram(program);
    }

    @PutMapping(path = "{programId}/student/{studentId}")
    public boolean addStudent(@PathVariable("programId") UUID programId, @PathVariable("studentId") UUID studentId) {
        return dynamoDbProgramService.addStudent(programId, studentId);
    }

    @DeleteMapping(path = "{programId}/student/{studentId}")
    public boolean removeStudent(@PathVariable("programId") UUID programId, @PathVariable("studentId") UUID studentId) {
        return dynamoDbProgramService.removeStudent(programId, studentId);
    }

    @DeleteMapping(path = "{programId}")
    public void deleteProgramById(@PathVariable("programId") UUID programId) {
        dynamoDbProgramService.deleteProgram(getProgramById(programId).getBody());
    }

}
