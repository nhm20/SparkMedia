package com.spark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spark.model.Post;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p WHERE p.user.id =:userId")
    public List<Post>findPostByUserId(Integer userId);

}
