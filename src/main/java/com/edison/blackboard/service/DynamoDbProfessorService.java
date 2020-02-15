package com.edison.blackboard.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.edison.blackboard.model.Professor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DynamoDbProfessorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBMapper.class);

    private final DynamoDBMapper mapper;

    @Autowired
    public DynamoDbProfessorService(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public void insertProfessor(Professor professor) {
        mapper.save(professor);
    }

    public Professor getProfessorById(UUID id) {
        return mapper.load(Professor.class, id);
    }

    public void updateProfessor(Professor professor) {
        try {
            mapper.save(professor, buildDynamoDBSaveExpression(professor));
        }
        catch (ConditionalCheckFailedException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public List<Professor> getAllProfessors() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(Professor.class, scanExpression);
    }


    public void deleteProfessor(Professor professor) {
        mapper.delete(professor);
    }

    public DynamoDBSaveExpression buildDynamoDBSaveExpression(Professor professor) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("professorid", new ExpectedAttributeValue(new AttributeValue(professor.getId().toString())));
        saveExpression.setExpected(expectedAttributeValueMap);
        return saveExpression;
    }
}
