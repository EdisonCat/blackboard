package com.edison.blackboard.api;

import com.edison.blackboard.model.Course;
import com.edison.blackboard.model.Professor;
import com.edison.blackboard.model.Program;
import com.edison.blackboard.model.Student;
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

    @GetMapping(path = "{programId}/student")
    public List<Student> getAllStudents(@PathVariable("programId") UUID programId) {
        return dynamoDbProgramService.getAllStudents(programId);
    }

    @GetMapping(path = "{programId}/professor")
    public List<Professor> getAllProfessors(@PathVariable("programId") UUID programId) {
        return dynamoDbProgramService.getAllProfessors(programId);
    }

    @GetMapping(path = "{programId}/course")
    public List<Course> getAllCourses(@PathVariable("programId") UUID programId) {
        return dynamoDbProgramService.getAllCourses(programId);
    }

    @PutMapping
    public void updateProgram(@RequestBody Program program) {
        dynamoDbProgramService.updateProgram(program);
    }

    @PutMapping(path = "{programId}/student/{studentId}")
    public boolean addStudent(@PathVariable("programId") UUID programId, @PathVariable("studentId") UUID studentId) {
        return dynamoDbProgramService.addStudent(programId, studentId);
    }

    @PutMapping(path = "{programId}/professor/{professorId}")
    public boolean addProfessor(@PathVariable("programId") UUID programId, @PathVariable("professorId") UUID professorId) {
        return dynamoDbProgramService.addProfessor(programId, professorId);
    }

    @PutMapping(path = "{programId}/course/{courseId}")
    public boolean addCourse(@PathVariable("programId") UUID programId, @PathVariable("courseId") UUID courseId) {
        return dynamoDbProgramService.addCourse(programId, courseId);
    }

    @DeleteMapping(path = "{programId}/course/{courseId}")
    public boolean removeCourse(@PathVariable("programId") UUID programId, @PathVariable("courseId") UUID courseId) {
        return dynamoDbProgramService.removeCourse(programId, courseId);
    }

    @DeleteMapping(path = "{programId}/professor/{professorId}")
    public boolean removeProfessor(@PathVariable("programId") UUID programId, @PathVariable("professorId") UUID professorId) {
        return dynamoDbProgramService.removeProfessor(programId, professorId);
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
