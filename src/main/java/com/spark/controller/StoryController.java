package com.spark.controller;

import com.spark.model.Story;
import com.spark.model.User;
import com.spark.services.StoryService;
import com.spark.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/story")
public class StoryController {
    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {
        User reqUser=userService.findUserByJwt(jwt);
        Story createdStory = storyService.createStory(story, reqUser);
        return createdStory;
    }
    @GetMapping("/find/user/{userId}")
    public List<Story> findStoryByUserId(@PathVariable Long userId, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser=userService.findUserByJwt(jwt);
        List<Story>stories=storyService.findStoryByUserId(userId);
        return stories;
    }
}









