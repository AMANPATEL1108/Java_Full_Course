package com.example.Databases_System_Design_016.message.repository;

import com.example.Databases_System_Design_016.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByChatIdAndIsDeletedOrderBySentAtAsc(Long chatId, Boolean isDeleted);

    List<Message> findByChatIdAndSenderIdAndIsDeleted(Long chatId, Long senderId, Boolean isDeleted);

    @Query("SELECT m FROM Message m WHERE m.chat.id = :chatId AND m.isDeleted = false " +
            "AND m.status != 'READ' AND m.sender.id != :userId ORDER BY m.sentAt ASC")
    List<Message> findUnreadMessages(Long chatId, Long userId);

    @Query("SELECT m FROM Message m WHERE m.chat.id = :chatId AND m.isDeleted = false " +
            "AND LOWER(m.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Message> searchMessages(Long chatId, String keyword);

    @Query("SELECT m FROM Message m WHERE m.chat.id = :chatId AND m.isDeleted = false " +
            "ORDER BY m.sentAt DESC LIMIT 1")
    Message findLatestMessage(Long chatId);

    Long countByChatIdAndIsDeletedAndStatusNot(Long chatId, Boolean isDeleted, Message.MessageStatus status);
}