package com.spark.services;

import com.spark.model.Story;
import com.spark.model.User;
import com.spark.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryService {
    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    public Story createStory(Story story, User user) {
        story.setUser(user);
        return storyRepository.save(story);
    }
    public List<Story>findStoryByUserId(Long userId)throws Exception{
        User user=userService.getUserById(userId);
        return storyRepository.findByUserId(userId);
    }

}
