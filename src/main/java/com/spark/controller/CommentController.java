package com.spark.controller;

import com.spark.model.Comment;
import com.spark.model.User;
import com.spark.services.CommentService;
import com.spark.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @PostMapping("/create/post/{postId}")
    public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization")String jwt, @PathVariable("postId") Long postId) throws Exception {
        User user=userService.findUserByJwt(jwt);
        Comment createdComment=commentService.createComment(comment,postId,user.getId());
        return createdComment;
    }
    @PutMapping("/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization")String jwt, @PathVariable Long commentId) throws Exception {
        User user=userService.findUserByJwt(jwt);
        Comment likeComment=commentService.likeComment(commentId,user.getId());
        return likeComment;
    }
}
