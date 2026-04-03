package com.example.Databases_System_Design_016.contact.service;

import com.example.Databases_System_Design_016.contact.dto.ContactRequest;
import com.example.Databases_System_Design_016.contact.dto.ContactResponse;

import java.util.List;

public interface ContactService {

    ContactResponse addContact(ContactRequest request);

    List<ContactResponse> getContactsByOwner(Long ownerId);

    List<ContactResponse> getBlockedContacts(Long ownerId);

    ContactResponse blockContact(Long ownerId, Long contactUserId);

    ContactResponse unblockContact(Long ownerId, Long contactUserId);

    ContactResponse updateNickname(Long ownerId, Long contactUserId, String nickname);

    void removeContact(Long ownerId, Long contactUserId);

    boolean isContact(Long ownerId, Long contactUserId);

    boolean isBlocked(Long ownerId, Long contactUserId);
}