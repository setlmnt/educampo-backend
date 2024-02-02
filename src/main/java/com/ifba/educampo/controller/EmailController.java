package com.ifba.educampo.controller;

import com.ifba.educampo.annotation.Log;
import com.ifba.educampo.dto.email.EmailResponseDto;
import com.ifba.educampo.enums.EmailStatusEnum;
import com.ifba.educampo.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Email", description = "Email API")
@RestController
@RequestMapping("/api/v1/emails")
@Log
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @Operation(summary = "List all emails with filters")
    @GetMapping
    public Page<EmailResponseDto> findAll(
            @RequestParam(required = false) String sender,
            @RequestParam(required = false) String recipient,
            @RequestParam(required = false) EmailStatusEnum status,
            Pageable pageable
    ) {
        return emailService.findAll(sender, recipient, status, pageable);
    }
}