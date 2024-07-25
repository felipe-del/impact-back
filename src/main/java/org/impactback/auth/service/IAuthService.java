package org.impactback.auth.service;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.impactback.auth.dto.ChangePasswordRequest;
import org.impactback.auth.dto.VerifyRequest;
import org.impactback.settings.security.UserDetailsImp;
import org.impactback.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author Isaac F. B. C.
 * @since 7/11/2024 - 8:53 PM
 */
@RequestMapping("/api/auth")
public interface IAuthService {


    User authenticate(String email, String password, HttpServletRequest request);
    User getCurrentUserSession();
    void forgotPassword(String email);
    ResponseEntity<String> verifyCode(VerifyRequest verifyRequest);
    User changePassword(ChangePasswordRequest changePasswordRequest);
}
