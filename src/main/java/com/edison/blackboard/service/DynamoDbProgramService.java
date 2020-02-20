package com.edison.blackboard.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.edison.blackboard.model.Course;
import com.edison.blackboard.model.Professor;
import com.edison.blackboard.model.Program;
import com.edison.blackboard.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DynamoDbProgramService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBMapper.class);

    private final DynamoDBMapper mapper;
    private final DynamoDbStudentService dynamoDbStudentService;
    private final DynamoDbProfessorService dynamoDbProfessorService;
    private final DynamoDbCourseService dynamoDbCourseService;

    @Autowired
    public DynamoDbProgramService(DynamoDBMapper mapper,
                                  @Lazy DynamoDbStudentService dynamoDbStudentService,
                                  @Lazy DynamoDbProfessorService dynamoDbProfessorService,
                                  @Lazy DynamoDbCourseService dynamoDbCourseService) {
        this.mapper = mapper;
        this.dynamoDbStudentService = dynamoDbStudentService;
        this.dynamoDbProfessorService = dynamoDbProfessorService;
        this.dynamoDbCourseService = dynamoDbCourseService;
    }

    public void insertProgram(Program program) {
        mapper.save(program);
    }

    public Program getProgramById(UUID id) {
        return mapper.load(Program.class, id);
    }

    public void updateProgram(Program program) {
        try {
            mapper.save(program, buildDynamoDBSaveExpression(program));
        } catch (ConditionalCheckFailedException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public List<Program> getAllPrograms() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(Program.class, scanExpression);
    }


    public void deleteProgram(Program program) {
        mapper.delete(program);
    }

    public DynamoDBSaveExpression buildDynamoDBSaveExpression(Program program) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("programid", new ExpectedAttributeValue(new AttributeValue(program.getId().toString())));
        saveExpression.setExpected(expectedAttributeValueMap);
        return saveExpression;
    }

    public boolean addStudent(UUID programId, UUID studentId) {
        updateProgram(getProgramById(programId).addStudent(dynamoDbStudentService.getStudentById(studentId)));
        dynamoDbStudentService.updateStudent(dynamoDbStudentService.getStudentById(studentId)
                .setProgram(getProgramById(programId).getName()));
        return true;
    }

    public boolean removeStudent(UUID programId, UUID studentId) {
        updateProgram(getProgramById(programId).removeStudent(dynamoDbStudentService.getStudentById(studentId)));
        dynamoDbStudentService.updateStudent(dynamoDbStudentService.getStudentById(studentId)
                .removeProgram(getProgramById(programId)));
        return true;
    }

    public boolean addProfessor(UUID programId, UUID professorId) {
        updateProgram(getProgramById(programId).addProfessor(dynamoDbProfessorService.getProfessorById(professorId)));
        dynamoDbProfessorService.updateProfessor(dynamoDbProfessorService.getProfessorById(professorId)
                .addProgram(getProgramById(programId)));
        return true;
    }

    public boolean removeProfessor(UUID programId, UUID professorId) {
        updateProgram(getProgramById(programId).addProfessor(dynamoDbProfessorService.getProfessorById(professorId)));
        dynamoDbProfessorService.updateProfessor(dynamoDbProfessorService.getProfessorById(professorId)
                .removeProgram(getProgramById(programId)));
        return true;
    }

    public boolean addCourse(UUID programId, UUID courseId) {
        updateProgram(getProgramById(programId).addCourse(dynamoDbCourseService.getCourseById(courseId)));
        return true;
    }

    public boolean removeCourse(UUID programId, UUID courseId) {
        updateProgram(getProgramById(programId).removeCourse(dynamoDbCourseService.getCourseById(courseId)));
        return true;
    }

    public List<Student> getAllStudents(UUID programId) {
        return getProgramById(programId).getStudentList();
    }

    public List<Professor> getAllProfessors(UUID programId) {
        return getProgramById(programId).getProfessorList();
    }

    public List<Course> getAllCourses(UUID programId) {
        return getProgramById(programId).getCourseList();
    }
}
