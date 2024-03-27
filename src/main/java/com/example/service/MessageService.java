package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    public MessageRepository messageRepository;
    public AccountRepository accountRepository;
    
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    
    public Message createMessage(Message message){
        Message newMessage = messageRepository.findByPostedby(message.getPosted_by());
        System.out.println(newMessage);
        if(newMessage == null || message.getMessage_text().isBlank() || message.getMessage_text().length() > 255){
            return null;
        }
        return messageRepository.save(message);
    }

   
     public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }
    
     public Message getMessagesById(int messageId){
         return messageRepository.findById(messageId).orElse(null);
        
     }
      public Integer deleteMessageById(int message_id){
       if(messageRepository.findById(message_id).orElse(null) != null ){
        messageRepository.deleteById(message_id);
        return 1;
       }
        return 0;
     }

     public Integer updateMessage(Integer message_id, Message message_text){
         Message updatedMessage = messageRepository.findById(message_id).orElse(message_text);
         Optional<Message> message = messageRepository.findById(message_id); 
         if(message.isEmpty() || message_text == null||message_text.getMessage_text().isBlank() || message_text.getMessage_text().length() > 255 ){
            return 0;
         }
            //updatedMessage.setMessage_text(message_text.getMessage_text());
            //updatedMessage.getMessage_text();
            messageRepository.save(updatedMessage);
               return 1;
          
    }
    public List<Message> retrieveAllMessagesByUser(List<Integer> posted_by){
        
        return messageRepository.findAllById(posted_by);
    }
   
}
