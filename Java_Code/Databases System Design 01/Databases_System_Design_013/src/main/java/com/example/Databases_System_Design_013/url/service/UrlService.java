package com.example.Databases_System_Design_013.url.service;

import com.example.Databases_System_Design_013.url.entity.Url;

import java.util.Optional;

public interface UrlService {

    Url createShortUrl(String originalUrl);

    Optional<Url> getByShortCode(String shortCode);
}
