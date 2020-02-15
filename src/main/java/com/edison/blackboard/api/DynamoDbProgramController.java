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

    @GetMapping(path = "{id}")
    public ResponseEntity<Program> getProgramById(@PathVariable("id") UUID id) {
        Program program = dynamoDbProgramService.getProgramById(id);
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

    @DeleteMapping(path = "{id}")
    public void deleteProgramById(@PathVariable("id") UUID id) {
        dynamoDbProgramService.deleteProgram(getProgramById(id).getBody());
    }

}
