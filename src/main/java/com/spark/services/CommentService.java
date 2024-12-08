package com.spark.services;

import com.spark.model.Comment;
import com.spark.model.Post;
import com.spark.model.User;
import com.spark.repository.CommentRepository;
import com.spark.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public Comment createComment(Comment comment, Long postId, Long userId) throws Exception {
        User user=userService.getUserById(userId);
        Post post=postService.findPostById(postId);
        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        Comment savedComment=commentRepository.save(comment);
        post.getComments().add(savedComment);
        postRepository.save(post);
        return savedComment;
    }
    public Comment getCommentById(Long commentId) throws Exception {
        Optional<Comment> opt = commentRepository.findById(commentId);
        if (opt.isEmpty()) {
            throw new Exception("Comment not exist");
        }
        return opt.get();
    }
    public Comment likeComment(Long commentId, Long userId) throws Exception {
        Comment comment = getCommentById(commentId);
        User user = userService.getUserById(userId);
        if(!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }
        else comment.getLiked().remove(user);
        return commentRepository.save(comment);
    }

}
