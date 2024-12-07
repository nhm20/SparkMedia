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

     @GetMapping("/api/{id}")
     public User getUserById(@PathVariable Integer id) {
          User user = userService.getUserById(id);
          if (user != null) {
               return user;
          }
          return null;
     }
     @PutMapping("/api/update")
     public User updateUser(@RequestHeader("Authorization")String jwt,@RequestBody User user) throws Exception {
          User reqUser= userService.findUserByJwt(jwt);
          User updatedUser= userService.updateUser(reqUser.getId(),user);
          return updatedUser;
     }
     @DeleteMapping("/api/delete/{id}")
     public String deleteUser(@PathVariable Integer id) {
          return userService.deleteUser(id);
     }

     @GetMapping("/api/follow/{followerId}")
     public User followUserHandle(@RequestHeader("Authorization") String jwt, @PathVariable Integer followerId) {
          User reqUser= userService.findUserByJwt(jwt);
          User user=userService.followUser(reqUser.getId(),followerId);
          return user;
     }

     @GetMapping("/api/searchbymail")
     public User getUserByEmail(@RequestParam("email") String email) {
          return userService.getUserByEmail(email);
     }

     @GetMapping("/api/search")
     public List<User> searchUser(@RequestParam("query") String query) {
          return userService.searchUser(query);
     }


     @GetMapping("/api/users/profile")
     public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
          User user= userService.findUserByJwt(jwt);
          return user;
     }

     @GetMapping("/api/users")
        public List<User> getUsers() {
            return userService.getUsers();
        }
}
