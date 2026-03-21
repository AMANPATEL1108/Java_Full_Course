package com.example.Databases_System_Design_013.url.serviceImpl;

import com.example.Databases_System_Design_013.url.repository.UrlRepository;
import com.example.Databases_System_Design_013.url.service.UrlService;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository repository;

    public UrlServiceImpl(UrlRepository repository) {
        this.repository = repository;
    }

}
