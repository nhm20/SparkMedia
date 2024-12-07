package com.spark.controller;

import com.spark.model.Post;
import com.spark.response.ApiResponse;
import com.spark.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/")
    public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable Integer userId) {
        return ResponseEntity.ok(postService.createPost(post, userId));
    }

    @DeleteMapping("/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) {
        String msg=postService.deletePost(postId, userId);
        ApiResponse res=new ApiResponse(msg,true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId) {
        List<Post> posts = postService.findPostsByUser(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> posts = postService.findAllPosts();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @PutMapping("/save/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId, @PathVariable Integer userId) {
        Post post = postService.savedPost(postId, userId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
    @PutMapping("/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) {
        Post post = postService.likePost(postId, userId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

}









