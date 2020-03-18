package com.edison.blackboard.api;

import com.edison.blackboard.service.DynamoDbAnnouncementService;
import com.edison.blackboard.service.DynamoDbBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/blackboard/dynamodb/board")
@RestController
public class DynamoDbBoardController {
    private final DynamoDbBoardService dynamoDbBoardService;
    private final DynamoDbAnnouncementService dynamoDbAnnouncementService;

    @Autowired
    public DynamoDbBoardController(DynamoDbBoardService dynamoDbBoardService, DynamoDbAnnouncementService dynamoDbAnnouncementService) {
        this.dynamoDbBoardService = dynamoDbBoardService;
        this.dynamoDbAnnouncementService = dynamoDbAnnouncementService;
    }

}
