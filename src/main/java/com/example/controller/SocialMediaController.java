package com.example.controller;

import java.util.List;

//import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;



/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
   
    AccountService accountService;

    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
          this.messageService = messageService;
    }

    

    @PostMapping ("/register")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
       //
        
            Account existUser = accountService.findByUsername(account.getUsername());
            if(existUser != null){
                 return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
            Account newAccount = accountService.createAccount(account);
            if(newAccount != null){
                return ResponseEntity.status(HttpStatus.OK).body(newAccount);
            } else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
    }
    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account){
        Account newLoggin = accountService.findByUsername(account.getUsername());
        if (newLoggin == null || !newLoggin.getPassword().equals(account.getPassword())){

             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        //   System.out.println(newLoggin.getPassword()); 
        //   System.out.println("Hello World!"); 
            return ResponseEntity.ok(newLoggin);
    
    }
    

    @PostMapping("/messages")
    public ResponseEntity <Message> createMessage(@RequestBody Message message){  
        Message newMessage = messageService.createMessage(message);
        if(newMessage == null){
           return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(newMessage);
        
    }


     @GetMapping("/messages")
     public ResponseEntity<List<Message>> getAllMessages(){
         List<Message> messages = messageService.getAllMessages();
         return ResponseEntity.ok(messages);
     }

     @GetMapping("/messages/{message_id}")
     public ResponseEntity <Message> getMessageById(@PathVariable(value = "message_id") Integer message){
         Message messageID =messageService.getMessagesById(message);
             return ResponseEntity.ok(messageID);
     }
        
 @DeleteMapping("//messages/{message_id}")
     public ResponseEntity <Integer> deleteMessage(@PathVariable Integer message_id){
          Integer deleteedMessage = messageService.deleteMessageById(message_id);
          if (deleteedMessage == 1){
            return ResponseEntity.status(HttpStatus.OK).body(deleteedMessage);
          }
          return null;
    }
@PatchMapping("/messages/{message_id}")
public ResponseEntity <Integer> updatedMessage(@PathVariable (value = "message_id") Integer message_id, @RequestBody Message message_text){
      Integer updatedMessage = messageService.updateMessage(message_id, message_text);
      if(updatedMessage == 1){
        return ResponseEntity.status(200).body(1);
      }
        return ResponseEntity.status(400).body(0);
    }

@GetMapping("accounts/{account_id}/messages")
public ResponseEntity <List<Message>> retrieveAllMessagesByUser(@PathVariable("account_id") List<Integer> account_id){
     List <Message> message = messageService.retrieveAllMessagesByUser(account_id);
    return ResponseEntity.status(HttpStatus.OK).body(message);
}
    
}
