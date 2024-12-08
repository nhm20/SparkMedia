package com.spark.services;

import com.spark.model.Chat;
import com.spark.model.Message;
import com.spark.model.User;
import com.spark.repository.ChatRepository;
import com.spark.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatRepository chatRepository;
    public Message createMessage(User user, Long chatId,Message req)throws Exception {
        {
            Message message = new Message();
            Chat chat = chatService.findChatById(chatId);
            message.setUser(user);
            message.setContent(req.getContent());
            message.setImage(req.getImage());
            message.setCreatedAt(LocalDateTime.now());
            message.setChat(chat);
            Message savedMsg= messageRepository.save(message);
            chat.getMessages().add(savedMsg);
            chatRepository.save(chat);
            return savedMsg;
        }
    }
    public List<Message>findChatsMessage(Long chatId)throws Exception{
        Chat chat=chatService.findChatById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}
