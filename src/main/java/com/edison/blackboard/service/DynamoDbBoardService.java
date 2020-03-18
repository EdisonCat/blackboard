package com.edison.blackboard.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.edison.blackboard.model.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DynamoDbBoardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Board.class);

    private final DynamoDBMapper mapper;
    private final DynamoDbAnnouncementService dynamoDbAnnouncementService;

    @Autowired
    public DynamoDbBoardService(DynamoDBMapper mapper, DynamoDbAnnouncementService dynamoDbAnnouncementService) {
        this.mapper = mapper;
        this.dynamoDbAnnouncementService = dynamoDbAnnouncementService;
    }

    public void insertBoard(Board board) {
        mapper.save(board);
    }

    public DynamoDBSaveExpression buildDynamoDBSaveExpression(Board board) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("boardid", new ExpectedAttributeValue(new AttributeValue(board.getBoardId().toString())));
        saveExpression.setExpected(expectedAttributeValueMap);
        return saveExpression;
    }
}
