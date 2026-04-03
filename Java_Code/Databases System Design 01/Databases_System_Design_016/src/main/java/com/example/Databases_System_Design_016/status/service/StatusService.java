package com.example.Databases_System_Design_016.status.service;

import com.example.Databases_System_Design_016.status.dto.StatusRequest;
import com.example.Databases_System_Design_016.status.dto.StatusResponse;

import java.util.List;

public interface StatusService {

    StatusResponse postStatus(StatusRequest request);

    StatusResponse getStatusById(Long id);

    List<StatusResponse> getActiveStatusesByUser(Long userId);

    List<StatusResponse> getAllStatusesByUser(Long userId);

    List<StatusResponse> getStatusFeed(List<Long> contactUserIds);

    StatusResponse viewStatus(Long statusId);

    void deleteStatus(Long id);

    void expireOldStatuses();
}