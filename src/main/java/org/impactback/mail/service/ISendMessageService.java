package org.impactback.mail.service;

import org.impactback.mail.dto.SendRequest;
import org.springframework.http.ResponseEntity;

/**
 * @author Isaac F. B. C.
 * @since 6/28/2024 - 1:54 PM
 */
public interface ISendMessageService {
    ResponseEntity<String> sendMessage(SendRequest sendRequest, boolean async);
}
