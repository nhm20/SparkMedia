package com.spark.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.spark.model.User;
import com.spark.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
     @Autowired
     private UserService userService;
     @PostMapping("/register")
     public User createUser(@RequestBody User user) {
          return userService.registerUser(user);
     }
     @GetMapping("/api/{id}")
     public User getUserById(@PathVariable Integer id) {
          User user = userService.getUserById(id);
          if (user != null) {
               return user;
          }
          return null;
     }
     @PutMapping("/api/update/{id}")
     public User updateUser(@PathVariable("id")Integer userId,@RequestBody User user) throws Exception {
          return userService.updateUser(userId,user);
     }
     @DeleteMapping("/api/delete/{id}")
     public String deleteUser(@PathVariable Integer id) {
          return userService.deleteUser(id);
     }

     @GetMapping("/api/follow/{userId}/{followerId}")
     public User followUserHandle(@PathVariable Integer userId, @PathVariable Integer followerId) {
          return userService.followUser(userId, followerId);
     }
     @GetMapping("/api/searchbymail")
     public User getUserByEmail(@RequestParam("email") String email) {
          return userService.getUserByEmail(email);
     }
     @GetMapping("/api/search")
     public List<User> searchUser(@RequestParam("query") String query) {
          return userService.searchUser(query);
     }


}
