package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository <Message, Integer>{
    @Query(value = "SELECT * FROM Message WHERE posted_by = ?1", nativeQuery = true)
     Message findByPostedby(int postedBy);

    // @Query(value = "SELECT * FROM Message WHERE posted_by = ?1", nativeQuery = true)
    //  List<Message> findByUserPostedBy(Integer postedBy);
    
    //  @Query(value = "SELECT * FROM Message WHERE message_id = ?2", nativeQuery = true)
    //  Message findBymessageId(Integer message_id);
     
}
