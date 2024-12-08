package com.spark.services;

import com.spark.model.Chat;
import com.spark.model.User;
import com.spark.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public Chat createChat(User reqUser, User user2) {
        Chat isExist = chatRepository.findChatByUsersId(user2, reqUser);
        if(isExist!=null){
            return isExist;
        }
        Chat chat = new Chat();
        chat.getUsers().add(reqUser);
        chat.getUsers().add(user2);
        chat.setCreatedAt(LocalDateTime.now());
        return chatRepository.save(chat);
    }
    public Chat findChatById(Long chatId) throws Exception {
        Optional<Chat>opt=chatRepository.findById(chatId);
        if(opt.isEmpty()){
            throw new Exception("Chat not found with id: "+chatId);
        }
        return opt.get();
    }
    public List<Chat> findUsersChat(Long userId){
        return chatRepository.findByUsersId(userId);
    }

}
