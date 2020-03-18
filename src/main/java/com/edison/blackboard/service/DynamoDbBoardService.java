package com.edison.blackboard.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.edison.blackboard.model.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
