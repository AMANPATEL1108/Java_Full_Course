package com.example.Databases_System_Design_015.hashtag.dto;

import com.example.Databases_System_Design_015.hashtag.entity.Hashtag;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HashtagResponse {

    private Long id;
    private String tag;
    private Long usageCount;
    private LocalDateTime createdAt;

    public static HashtagResponse from(Hashtag h) {
        return HashtagResponse.builder()
                .id(h.getId())
                .tag(h.getTag())
                .usageCount(h.getUsageCount())
                .createdAt(h.getCreatedAt())
                .build();
    }
}