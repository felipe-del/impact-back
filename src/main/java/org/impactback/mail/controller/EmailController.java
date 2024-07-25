package org.impactback.mail.controller;

import org.impactback.mail.dto.SendRequest;
import org.impactback.mail.service.implement.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Isaac F. B. C.
 * @since 6/28/2024 - 11:27 AM
 */
@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final SendMessageService sendMessageService;

    @Autowired
    public EmailController(SendMessageService emailService) {
        this.sendMessageService = emailService;
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@RequestBody SendRequest sendRequest) {
        return sendMessageService.sendMessage(sendRequest, false);
    }

}
