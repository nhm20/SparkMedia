package com.spark.controller;

import com.spark.Exceptions.UserException;
import com.spark.model.Post;
import com.spark.model.User;
import com.spark.response.ApiResponse;
import com.spark.services.PostService;
import com.spark.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization")String jwt, @RequestBody Post post) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        return ResponseEntity.ok(postService.createPost(post, reqUser.getId()));
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization")String jwt,@PathVariable Long postId) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        String msg=postService.deletePost(postId, reqUser.getId());
        ApiResponse res=new ApiResponse(msg,true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Long postId) {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Long userId) {
        List<Post> posts = postService.findPostsByUser(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> posts = postService.findAllPosts();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @PutMapping("/save/{postId}")
    public ResponseEntity<Post> savedPostHandler(@RequestHeader("Authorization")String jwt,@PathVariable Long postId)throws UserException  {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.savedPost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
    @PutMapping("/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@RequestHeader("Authorization")String jwt,@PathVariable Long postId) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.likePost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
}









