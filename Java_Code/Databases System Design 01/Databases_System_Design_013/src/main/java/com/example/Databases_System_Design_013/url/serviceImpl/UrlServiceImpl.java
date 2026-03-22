package com.example.Databases_System_Design_013.url.serviceImpl;

import com.example.Databases_System_Design_013.url.entity.Url;
import com.example.Databases_System_Design_013.url.repository.UrlRepository;
import com.example.Databases_System_Design_013.url.service.UrlService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository repository;
    private final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final Random random = new Random();

    public UrlServiceImpl(UrlRepository repository) {
        this.repository = repository;
    }

    public Url createShortUrl(String originalUrl) {
        String shortCode = generateUniqueCode();

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortCode);

        return repository.save(url);
    }

    public Optional<Url> getByShortCode(String shortCode) {
        Optional<Url> urlOpt = repository.findByShortCode(shortCode);

        urlOpt.ifPresent(url -> {
            url.setClickCount(url.getClickCount() + 1);
            repository.save(url);
        });

        return urlOpt;
    }

    private String generateUniqueCode() {
        String code;
        do {
            code = randomString(6);
        } while (repository.existsByShortCode(code));

        return code;
    }

    private String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }


}
