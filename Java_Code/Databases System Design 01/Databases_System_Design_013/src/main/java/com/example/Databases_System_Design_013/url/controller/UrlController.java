package com.example.Databases_System_Design_013.url.controller;

import com.example.Databases_System_Design_013.url.entity.Url;
import com.example.Databases_System_Design_013.url.service.RateLimiterService;
import com.example.Databases_System_Design_013.url.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService service;
    private final RateLimiterService rateLimiterService;

    public UrlController(UrlService service, RateLimiterService rateLimiterService) {
        this.service = service;
        this.rateLimiterService = rateLimiterService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<?> shorten(
            @RequestParam String originalUrl,
            HttpServletRequest request) {

        String ip = request.getRemoteAddr();

        if (!rateLimiterService.allowRequest(ip)) {
            return ResponseEntity.status(429)
                    .body("Too many requests. Try again later.");
        }

        Url url = service.createShortUrl(originalUrl);

        return ResponseEntity.ok(
                "http://localhost:8080/api/s/" + url.getShortCode()
        );
    }

    @GetMapping("/s/{code}")
    public ResponseEntity<?> redirect(@PathVariable String code) {
        return service.getByShortCode(code)
                .map(url -> ResponseEntity.status(302)
                        .header("Location", url.getOriginalUrl())
                        .build())
                .orElse(ResponseEntity.notFound().build());
    }
}