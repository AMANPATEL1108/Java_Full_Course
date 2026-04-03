package com.example.Databases_System_Design_016.contact.repository;

import com.example.Databases_System_Design_016.contact.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByOwnerId(Long ownerId);

    Optional<Contact> findByOwnerIdAndContactUserId(Long ownerId, Long contactUserId);

    boolean existsByOwnerIdAndContactUserId(Long ownerId, Long contactUserId);

    void deleteByOwnerIdAndContactUserId(Long ownerId, Long contactUserId);

    List<Contact> findByOwnerIdAndIsBlocked(Long ownerId, Boolean isBlocked);
}