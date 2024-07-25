package org.impactback.auth.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.impactback.auth.dto.ChangePasswordRequest;
import org.impactback.auth.dto.VerifyRequest;
import org.impactback.auth.service.implement.AuthService;
import org.impactback.settings.exceptions.dto.SuccessResponseDTO;
import org.impactback.user.entity.User;
import org.impactback.settings.security.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Isaac F. B. C.
 * @since 6/27/2024 - 10:56 PM
 */

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    /**
     * Handles the login request for a user.
     *
     * @param user the details of the user attempting to log in (in JSON format from the request body)
     * @param request   the HTTP request
     * @return ResponseEntity with the authenticated user (without the password) if the login is successful
     * @throws ResponseStatusException if login fails, returning an HTTP 401 (UNAUTHORIZED) error
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user, HttpServletRequest request) {
        User authenticatedUser = authService.authenticate(user.getEmail(), user.getPassword(), request);
        return ResponseEntity.ok().body(authenticatedUser);
    }
    /**
     * Handles the logout request for a user.
     *
     * @param request the HTTP request containing the user's session information
     * @return ResponseEntity with no content and HTTP 204 (NO CONTENT) if the logout is successful
     * @throws ResponseStatusException if an error occurs during logout, returning an HTTP 500 (INTERNAL SERVER ERROR) error
     */
    @PostMapping("/logout")
    public ResponseEntity<SuccessResponseDTO> logout(HttpServletRequest request) {
        try {
            request.logout();
            System.out.println("Usuario desconectado");
            return ResponseEntity.ok(new SuccessResponseDTO("Logout successful"));
        } catch (ServletException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Logout failed", e);
        }
    }
    /**
     * Retrieves the information of the currently authenticated user.
     *
     * @param user the details of the currently authenticated user
     * @return ResponseEntity with the user's information (without the password) and HTTP 200 (OK)
     * @throws ResponseStatusException if the user is not authenticated, returning an HTTP 401 (UNAUTHORIZED) error
     */
    @GetMapping("/current-user")
    public ResponseEntity<User> getCurrentUserSession(@AuthenticationPrincipal UserDetailsImp user) {
        User userSession = authService.getCurrentUserSession();
        return ResponseEntity.ok(userSession);
    }
    /**
     * Initiates the password recovery process by sending a reset email.
     *
     * @param request a map containing the email of the user requesting password recovery
     * @return ResponseEntity with a success message and HTTP 200 (OK)
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        authService.forgotPassword(email);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Password reset email sent successfully");
        return ResponseEntity.ok(response);
    }
    /**
     * Verifies the password recovery code provided by the user.
     *
     * @param verifyRequest the object containing the verification code
     * @return ResponseEntity with a success message and HTTP 200 (OK)
     */
    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestBody VerifyRequest verifyRequest) {
        System.out.printf(verifyRequest.getCode());
        authService.verifyCode(verifyRequest);
        return ResponseEntity.ok("Code verified successfully");
    }
    /**
     * Changes the user's password.
     *
     * @param request the object containing the new password
     * @return ResponseEntity with the updated user and HTTP 200 (OK)
     */
    @PostMapping("/change-password")
    public ResponseEntity<User> changePassword(@RequestBody ChangePasswordRequest request) {
        System.out.println(request.toString());
        User user = authService.changePassword(request);
        return ResponseEntity.ok(user);
    }
}
