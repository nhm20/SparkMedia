package com.spark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spark.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

     @Query("select u from User u where u.email = :email")
     public User findByEmail(@Param("email") String email);

     @Query("select u from User u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
     List<User> searchUser(@Param("query") String query);
}
