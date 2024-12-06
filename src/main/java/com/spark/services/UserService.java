package com.spark.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.model.User;
import com.spark.repository.UserRepository;

@Service
public class UserService {
     @Autowired
     private UserRepository userRepo;

     public User registerUser(User user) {
          userRepo.save(user);
          return user;
     }

     public User getUserById(Integer id) {
          return userRepo.findById(id).orElse(null);
     }

     public User updateUser(Integer id, User user) {
          User newUser = getUserById(id);
          if (newUser != null) {
               if (user.getFirstName() != null && !user.getFirstName().isEmpty()) newUser.setFirstName(user.getFirstName());
               if (user.getLastName() != null && !user.getLastName().isEmpty()) newUser.setLastName(user.getLastName());
               if (user.getEmail() != null && !user.getEmail().isEmpty()) newUser.setEmail(user.getEmail());
               if (user.getPassword() != null && !user.getPassword().isEmpty()) newUser.setPassword(user.getPassword());
               userRepo.save(newUser);
               return newUser;
          }
          return null;
     }

     public String deleteUser(Integer id) {
          Optional<User> user = userRepo.findById(id);
          if (user.isPresent()) {
               userRepo.delete(user.get());
               return "User deleted with id: " + id;
          }
          return "User not found with id: " + id;
     }

     public List<User> getUsers() {
          return userRepo.findAll();
     }

     public User getUserByEmail(String email) {
          return userRepo.findByEmail(email);
     }

     public User followUser(Integer uId1, Integer uId2) {
          User user1 = getUserById(uId1);
          User user2 = getUserById(uId2);
          if (user1 != null && user2 != null) {
               List<Integer> followers = Optional.ofNullable(user1.getFollowers()).orElse(new ArrayList<>());
               List<Integer> followings = Optional.ofNullable(user2.getFollowings()).orElse(new ArrayList<>());
               followers.add(uId2);
               followings.add(uId1);
               user1.setFollowers(followers);
               user2.setFollowings(followings);
               userRepo.save(user1);
               userRepo.save(user2);
               return user1;
          }
          return null;
     }

     public List<User> searchUser(String query) {
          List<User> users = userRepo.searchUser(query);
          if (users != null) {
               return users;
          }
          return new ArrayList<>();
     }
}