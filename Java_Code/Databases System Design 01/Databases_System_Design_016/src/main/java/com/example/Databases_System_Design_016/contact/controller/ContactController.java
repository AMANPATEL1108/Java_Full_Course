package com.example.Databases_System_Design_016.contact.controller;

import com.example.Databases_System_Design_016.contact.dto.ContactRequest;
import com.example.Databases_System_Design_016.contact.dto.ContactResponse;
import com.example.Databases_System_Design_016.contact.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    // POST http://localhost:8080/api/v1/contacts
    @PostMapping
    public ResponseEntity<ContactResponse> addContact(@Valid @RequestBody ContactRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.addContact(request));
    }

    // GET http://localhost:8080/api/v1/contacts/owner/{ownerId}
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ContactResponse>> getContactsByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(contactService.getContactsByOwner(ownerId));
    }

    // GET http://localhost:8080/api/v1/contacts/owner/{ownerId}/blocked
    @GetMapping("/owner/{ownerId}/blocked")
    public ResponseEntity<List<ContactResponse>> getBlockedContacts(@PathVariable Long ownerId) {
        return ResponseEntity.ok(contactService.getBlockedContacts(ownerId));
    }

    // GET http://localhost:8080/api/v1/contacts/owner/{ownerId}/contact/{contactUserId}/check
    @GetMapping("/owner/{ownerId}/contact/{contactUserId}/check")
    public ResponseEntity<Boolean> isContact(@PathVariable Long ownerId,
                                             @PathVariable Long contactUserId) {
        return ResponseEntity.ok(contactService.isContact(ownerId, contactUserId));
    }

    // PATCH http://localhost:8080/api/v1/contacts/owner/{ownerId}/contact/{contactUserId}/block
    @PatchMapping("/owner/{ownerId}/contact/{contactUserId}/block")
    public ResponseEntity<ContactResponse> blockContact(@PathVariable Long ownerId,
                                                        @PathVariable Long contactUserId) {
        return ResponseEntity.ok(contactService.blockContact(ownerId, contactUserId));
    }

    // PATCH http://localhost:8080/api/v1/contacts/owner/{ownerId}/contact/{contactUserId}/unblock
    @PatchMapping("/owner/{ownerId}/contact/{contactUserId}/unblock")
    public ResponseEntity<ContactResponse> unblockContact(@PathVariable Long ownerId,
                                                          @PathVariable Long contactUserId) {
        return ResponseEntity.ok(contactService.unblockContact(ownerId, contactUserId));
    }

    // PATCH http://localhost:8080/api/v1/contacts/owner/{ownerId}/contact/{contactUserId}/nickname?nickname=Bro
    @PatchMapping("/owner/{ownerId}/contact/{contactUserId}/nickname")
    public ResponseEntity<ContactResponse> updateNickname(@PathVariable Long ownerId,
                                                          @PathVariable Long contactUserId,
                                                          @RequestParam String nickname) {
        return ResponseEntity.ok(contactService.updateNickname(ownerId, contactUserId, nickname));
    }

    // DELETE http://localhost:8080/api/v1/contacts/owner/{ownerId}/contact/{contactUserId}
    @DeleteMapping("/owner/{ownerId}/contact/{contactUserId}")
    public ResponseEntity<Void> removeContact(@PathVariable Long ownerId,
                                              @PathVariable Long contactUserId) {
        contactService.removeContact(ownerId, contactUserId);
        return ResponseEntity.noContent().build();
    }
}