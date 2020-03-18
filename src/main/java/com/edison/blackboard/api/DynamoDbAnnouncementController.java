package com.edison.blackboard.api;

import com.edison.blackboard.model.Announcement;
import com.edison.blackboard.service.DynamoDbAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/blackboard/dynamodb/announcement")
@RestController
public class DynamoDbAnnouncementController {
    private final DynamoDbAnnouncementService dynamoDbAnnouncementService;

    @Autowired
    public DynamoDbAnnouncementController(DynamoDbAnnouncementService dynamoDbAnnouncementService) {
        this.dynamoDbAnnouncementService = dynamoDbAnnouncementService;
    }

    @PostMapping
    public boolean insertAnnouncement(@RequestBody Announcement announcement) {
        dynamoDbAnnouncementService.insertAnnouncement(announcement);
        return true;
    }

    @GetMapping(path = "{announcementId}")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable("announcementId")UUID announcementId) {
        Announcement announcement = dynamoDbAnnouncementService.getAnnouncementById(announcementId);
        return new ResponseEntity<>(announcement, HttpStatus.OK);
    }

    @GetMapping
    public List<Announcement> getAllAnnouncements() {
        return dynamoDbAnnouncementService.getAllAnnouncements();
    }

    @PutMapping
    public void updateAnnouncement(@RequestBody Announcement announcement) {
        dynamoDbAnnouncementService.updateAnnouncement(announcement);
    }

    @DeleteMapping(path = "{announcementId}")
    public void deleteAnnouncementById(@PathVariable("announcementId") UUID announcementId) {
        dynamoDbAnnouncementService.deleteAnnouncement(getAnnouncementById(announcementId).getBody());
    }
}
