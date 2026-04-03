package com.example.Databases_System_Design_016.chat.repository;

import com.example.Databases_System_Design_016.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT c FROM Chat c WHERE c.chatType = 'PRIVATE' AND " +
            "((c.sender.id = :userId1 AND c.receiver.id = :userId2) OR " +
            "(c.sender.id = :userId2 AND c.receiver.id = :userId1))")
    Optional<Chat> findPrivateChat(Long userId1, Long userId2);

    @Query("SELECT c FROM Chat c WHERE (c.sender.id = :userId OR c.receiver.id = :userId) " +
            "AND c.isActive = true ORDER BY c.lastMessageAt DESC NULLS LAST")
    List<Chat> findAllPrivateChatsByUser(Long userId);

    List<Chat> findByGroupIdAndIsActive(Long groupId, Boolean isActive);

    @Query("SELECT c FROM Chat c WHERE c.chatType = 'GROUP' AND c.group.id IN " +
            "(SELECT gm.group.id FROM GroupMember gm WHERE gm.user.id = :userId) " +
            "AND c.isActive = true ORDER BY c.lastMessageAt DESC NULLS LAST")
    List<Chat> findAllGroupChatsByUser(Long userId);
}