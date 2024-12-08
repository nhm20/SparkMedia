package com.spark.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.spark.Exceptions.UserException;
import com.spark.config.JwtProvider;
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

     public User getUserById(Long id)throws UserException {
          Optional<User>user=userRepo.findById(id);
            if(user.isPresent()){
                 return user.get();
            }
            throw new UserException("User not found with id: "+id);
     }

     public User updateUser(Long id, User user)throws UserException {
          Optional<User>user1=userRepo.findById(id);
          if (user1.isEmpty()) {
               throw new UserException("User not found with id: " + id);
          }
          User newUser = user1.get();
          if (user.getFirstName() != null && !user.getFirstName().isEmpty()) newUser.setFirstName(user.getFirstName());
          if (user.getLastName() != null && !user.getLastName().isEmpty()) newUser.setLastName(user.getLastName());
          if (user.getEmail() != null && !user.getEmail().isEmpty()) newUser.setEmail(user.getEmail());
          if (user.getPassword() != null && !user.getPassword().isEmpty()) newUser.setPassword(user.getPassword());
          if(user.getGender()!=null && !user.getGender().isEmpty()) newUser.setGender(user.getGender());
          userRepo.save(newUser);
          return newUser;
     }

     public String deleteUser(Long id) {
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

     public User followUser(Long reqUserId, Long uId2)throws UserException {
          User reqUser = getUserById(reqUserId);
          User user2 = getUserById(uId2);
          user2.getFollowers().add(reqUser.getId());
          reqUser.getFollowers().add(user2.getId());
            userRepo.save(reqUser);
            userRepo.save(user2);
          return reqUser;
     }

     public List<User> searchUser(String query) {
          List<User> users = userRepo.searchUser(query);
          if (users != null) {
               return users;
          }
          return new ArrayList<>();
     }

     public User findUserByJwt(String jwt) {
          String email = JwtProvider.getEmailFromJwtToken(jwt);
          return userRepo.findByEmail(email);
     }
}