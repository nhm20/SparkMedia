package com.spark.controller;

import com.spark.Exceptions.UserException;
import com.spark.model.Chat;
import com.spark.model.User;
import com.spark.request.CreateChatRequest;
import com.spark.services.ChatService;
import com.spark.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public Chat createChat(@RequestHeader("Authorization")String jwt,@RequestBody CreateChatRequest req)throws UserException {
        User reqUser=userService.findUserByJwt(jwt);
        User user2=userService.getUserById(req.getUserId());
        Chat chat=chatService.createChat(reqUser,user2);
        return chat;
    }

    @GetMapping("/{chatId}")
    public List<Chat>findUsersChat(@RequestHeader("Authorization")String jwt){
        User user=userService.findUserByJwt(jwt);
        return chatService.findUsersChat(user.getId());
    }


}
