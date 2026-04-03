package com.example.Databases_System_Design_016.status.service.impl;

import com.example.Databases_System_Design_016.status.dto.StatusRequest;
import com.example.Databases_System_Design_016.status.dto.StatusResponse;
import com.example.Databases_System_Design_016.status.entity.Status;
import com.example.Databases_System_Design_016.status.repository.StatusRepository;
import com.example.Databases_System_Design_016.status.service.StatusService;
import com.example.Databases_System_Design_016.user.entity.User;
import com.example.Databases_System_Design_016.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;
    private final UserRepository userRepository;

    @Override
    public StatusResponse postStatus(StatusRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        if (request.getStatusType() == Status.StatusType.TEXT && request.getCaption() == null) {
            throw new RuntimeException("Caption is required for TEXT status");
        }
        if (request.getStatusType() != Status.StatusType.TEXT && request.getMediaUrl() == null) {
            throw new RuntimeException("Media URL is required for IMAGE/VIDEO status");
        }

        Status status = Status.builder()
                .user(user)
                .statusType(request.getStatusType())
                .caption(request.getCaption())
                .mediaUrl(request.getMediaUrl())
                .backgroundColor(request.getBackgroundColor())
                .fontStyle(request.getFontStyle())
                .viewCount(0L)
                .isActive(true)
                .build();

        return StatusResponse.from(statusRepository.save(status));
    }

    @Override
    @Transactional(readOnly = true)
    public StatusResponse getStatusById(Long id) {
        return StatusResponse.from(findStatusOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatusResponse> getActiveStatusesByUser(Long userId) {
        return statusRepository
                .findByUserIdAndIsActiveAndExpiresAtAfterOrderByCreatedAtDesc(
                        userId, true, LocalDateTime.now())
                .stream()
                .map(StatusResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatusResponse> getAllStatusesByUser(Long userId) {
        return statusRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(StatusResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatusResponse> getStatusFeed(List<Long> contactUserIds) {
        if (contactUserIds == null || contactUserIds.isEmpty()) {
            return List.of();
        }
        return statusRepository.findActiveStatusesByUserIds(contactUserIds, LocalDateTime.now())
                .stream()
                .map(StatusResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public StatusResponse viewStatus(Long statusId) {
        Status status = findStatusOrThrow(statusId);

        if (!status.getIsActive() || status.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Status has expired or is no longer available");
        }

        status.setViewCount(status.getViewCount() + 1);
        return StatusResponse.from(statusRepository.save(status));
    }

    @Override
    public void deleteStatus(Long id) {
        Status status = findStatusOrThrow(id);
        status.setIsActive(false);
        statusRepository.save(status);
    }

    @Override
    public void expireOldStatuses() {
        List<Status> expired = statusRepository.findExpiredStatuses(LocalDateTime.now());
        expired.forEach(s -> s.setIsActive(false));
        statusRepository.saveAll(expired);
    }

    private Status findStatusOrThrow(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found with id: " + id));
    }
}