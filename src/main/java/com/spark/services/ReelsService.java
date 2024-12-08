package com.spark.services;

import com.spark.model.Reels;
import com.spark.model.User;
import com.spark.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsService {
    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;

    public Reels saveReels(Reels reels, User user) {
        reels.setUser(user);
        return reelsRepository.save(reels);
    }

    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }
    public List<Reels> findReelsByUser(Long  userId) throws Exception {
        return reelsRepository.findByUserId(userId);
    }

}
