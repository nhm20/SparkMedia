package com.spark.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.spark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.model.Post;
import com.spark.model.User;
import com.spark.repository.PostRepository;

@Service
public class PostService {
     @Autowired
     private PostRepository postRepo;

     @Autowired
     private UserService userService;
     @Autowired
     private UserRepository userRepo;
     public Post createPost(Post post, Integer userId) {
          User user = userService.getUserById(userId);
          Post newPost = new Post();
          newPost.setCaption(post.getCaption());
          newPost.setImage(post.getImage());
          newPost.setVideo(post.getVideo());
          newPost.setUser(user);
          newPost.setCreatedAt(LocalDate.now());
          postRepo.save(newPost);
          return newPost;
     }

     public String deletePost(Integer pid,Integer uid) {
          Optional<Post> post = postRepo.findById(pid);
          User user = userService.getUserById(uid);
          if(post.isPresent()) {
               if(post.get().getUser().getId()==uid) {
                    postRepo.delete(post.get());
                    return "Post deleted with id: " + pid;
               }
               return "You are not authorized to delete this post";
          }
          return null;
     }

     public List<Post> findPostsByUser(Integer userId) {
          return postRepo.findPostByUserId(userId);
     }

     public Post findPostById(Integer postId) {
          Optional<Post> opt=postRepo.findById(postId);
            if(opt.isPresent()) {
                 return opt.get();
            }
          return null;
     }

     public List<Post> findAllPosts() {
          return postRepo.findAll();
     }

     public Post savedPost(Integer pid,Integer uid) {
           Post post = findPostById(pid);
           User user = userService.getUserById(uid);
           if(user.getSavedPost().contains(post)) {
                   user.getSavedPost().remove(post);
           }
           else {
                   user.getSavedPost().add(post);
           }
           userRepo.save(user);
          return post;
     }
     public Post likePost(Integer pid,Integer uid) {
          Post post = findPostById(pid);
            User user = userService.getUserById(uid);
            if(post.getLiked().contains(user)) {
                    post.getLiked().remove(user);
            }
            else {
                    post.getLiked().add(user);
            }
          return postRepo.save(post);
     }

}
