package com.edison.blackboard.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.edison.blackboard.model.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DynamoDbProgramService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBMapper.class);

    private final DynamoDBMapper mapper;

    @Autowired
    public DynamoDbProgramService(DynamoDBMapper mapper) {
        this.mapper = mapper;
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
}
