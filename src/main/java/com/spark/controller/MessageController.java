package com.spark.controller;

import com.spark.model.Message;
import com.spark.model.User;
import com.spark.services.MessageService;
import com.spark.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("/create/{chatId}")
    public Message createMessage(@RequestBody Message req, @RequestHeader("Authorization")String jwt,@PathVariable Long chatId) throws Exception {
        User user=userService.findUserByJwt(jwt);
        return messageService.createMessage(user,chatId,req);
    }
    @GetMapping("/find/{chatId}")
    public List<Message> findChatsMessage(@RequestHeader("Authorization")String jwt, @PathVariable Long chatId) throws Exception {
        User user=userService.findUserByJwt(jwt);
        return messageService.findChatsMessage(chatId);
    }
}
