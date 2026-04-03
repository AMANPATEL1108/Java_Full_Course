package com.example.Databases_System_Design_016.contact.service.impl;

import com.example.Databases_System_Design_016.contact.dto.ContactRequest;
import com.example.Databases_System_Design_016.contact.dto.ContactResponse;
import com.example.Databases_System_Design_016.contact.entity.Contact;
import com.example.Databases_System_Design_016.contact.repository.ContactRepository;
import com.example.Databases_System_Design_016.contact.service.ContactService;
import com.example.Databases_System_Design_016.user.entity.User;
import com.example.Databases_System_Design_016.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @Override
    public ContactResponse addContact(ContactRequest request) {
        if (request.getOwnerId().equals(request.getContactUserId())) {
            throw new RuntimeException("Cannot add yourself as a contact");
        }
        if (contactRepository.existsByOwnerIdAndContactUserId(request.getOwnerId(), request.getContactUserId())) {
            throw new RuntimeException("Contact already exists");
        }
        User owner = findUserOrThrow(request.getOwnerId());
        User contactUser = findUserOrThrow(request.getContactUserId());

        Contact contact = Contact.builder()
                .owner(owner)
                .contactUser(contactUser)
                .nickname(request.getNickname())
                .isBlocked(false)
                .build();
        return ContactResponse.from(contactRepository.save(contact));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactResponse> getContactsByOwner(Long ownerId) {
        return contactRepository.findByOwnerId(ownerId).stream()
                .map(ContactResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactResponse> getBlockedContacts(Long ownerId) {
        return contactRepository.findByOwnerIdAndIsBlocked(ownerId, true).stream()
                .map(ContactResponse::from).collect(Collectors.toList());
    }

    @Override
    public ContactResponse blockContact(Long ownerId, Long contactUserId) {
        Contact contact = findContactOrThrow(ownerId, contactUserId);
        contact.setIsBlocked(true);
        return ContactResponse.from(contactRepository.save(contact));
    }

    @Override
    public ContactResponse unblockContact(Long ownerId, Long contactUserId) {
        Contact contact = findContactOrThrow(ownerId, contactUserId);
        contact.setIsBlocked(false);
        return ContactResponse.from(contactRepository.save(contact));
    }

    @Override
    public ContactResponse updateNickname(Long ownerId, Long contactUserId, String nickname) {
        Contact contact = findContactOrThrow(ownerId, contactUserId);
        contact.setNickname(nickname);
        return ContactResponse.from(contactRepository.save(contact));
    }

    @Override
    public void removeContact(Long ownerId, Long contactUserId) {
        if (!contactRepository.existsByOwnerIdAndContactUserId(ownerId, contactUserId)) {
            throw new RuntimeException("Contact not found");
        }
        contactRepository.deleteByOwnerIdAndContactUserId(ownerId, contactUserId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isContact(Long ownerId, Long contactUserId) {
        return contactRepository.existsByOwnerIdAndContactUserId(ownerId, contactUserId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isBlocked(Long ownerId, Long contactUserId) {
        return contactRepository.findByOwnerIdAndContactUserId(ownerId, contactUserId)
                .map(Contact::getIsBlocked)
                .orElse(false);
    }

    private Contact findContactOrThrow(Long ownerId, Long contactUserId) {
        return contactRepository.findByOwnerIdAndContactUserId(ownerId, contactUserId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    private User findUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}