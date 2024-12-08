package com.spark.controller;

import com.spark.model.Reels;
import com.spark.model.User;
import com.spark.services.ReelsService;
import com.spark.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reels")
public class ReelsController {
    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public Reels createReels(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt) {
        User reqUser=userService.findUserByJwt(jwt);
        Reels newReels = reelsService.saveReels(reels, reqUser);
        return newReels;
    }
    @GetMapping("/all/user/{userId}")
    public List<Reels> getAllReels(@PathVariable Long userId) throws Exception {
        return reelsService.findReelsByUser(userId);
    }
}
