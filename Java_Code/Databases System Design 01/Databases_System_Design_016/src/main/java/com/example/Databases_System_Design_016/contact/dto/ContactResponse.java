package com.example.Databases_System_Design_016.contact.dto;

import com.example.Databases_System_Design_016.contact.entity.Contact;
import com.example.Databases_System_Design_016.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ContactResponse {

    private Long id;
    private Long ownerId;
    private Long contactUserId;
    private String contactPhoneNumber;
    private String contactDisplayName;
    private String contactProfilePictureUrl;
    private User.OnlineStatus onlineStatus;
    private String nickname;
    private Boolean isBlocked;
    private LocalDateTime createdAt;

    public static ContactResponse from(Contact c) {
        return ContactResponse.builder()
                .id(c.getId())
                .ownerId(c.getOwner().getId())
                .contactUserId(c.getContactUser().getId())
                .contactPhoneNumber(c.getContactUser().getPhoneNumber())
                .contactDisplayName(c.getNickname() != null
                        ? c.getNickname()
                        : c.getContactUser().getDisplayName())
                .contactProfilePictureUrl(c.getContactUser().getProfilePictureUrl())
                .onlineStatus(c.getContactUser().getOnlineStatus())
                .nickname(c.getNickname())
                .isBlocked(c.getIsBlocked())
                .createdAt(c.getCreatedAt())
                .build();
    }
}