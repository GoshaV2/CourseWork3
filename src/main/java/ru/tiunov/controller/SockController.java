package ru.tiunov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.tiunov.dto.SockDto;
import ru.tiunov.service.SockService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestControllerAdvice
@RequestMapping("/api/socks")
@Validated
public class SockController {
    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @PostMapping
    public ResponseEntity addSocks(@Valid @RequestBody SockDto.Request.Standard sock) {
        sockService.addSocks(sock);
        return ResponseEntity.ok(sock);
    }

    @GetMapping
    private ResponseEntity getSocks(@Valid @RequestBody SockDto.Request.GetWithOptions sock){
        return ResponseEntity.ok(sockService.getQuantityOfSocks(sock));
    }
}
