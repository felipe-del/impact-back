package org.impactback.auth.service.implement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.impactback.auth.dto.ChangePasswordRequest;
import org.impactback.auth.dto.VerifyRequest;
import org.impactback.auth.service.IAuthService;
import org.impactback.mail.EmailServiceUtil;
import org.impactback.mail.dto.SendRequest;
import org.impactback.mail.service.implement.SendMessageService;
import org.impactback.settings.security.UserDetailsImp;
import org.impactback.token.entity.Token;
import org.impactback.token.service.implement.TokenService;
import org.impactback.user.entity.User;
import org.impactback.user.service.implement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

/**
 * @author Isaac F. B. C.
 * @since 7/11/2024 - 9:02 PM
 */
@Service
public class AuthService implements IAuthService {
    private final SendMessageService emailService;
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public AuthService(SendMessageService emailService, UserService userService, TokenService tokenService) {
        this.emailService = emailService;
        this.userService = userService;
        this.tokenService = tokenService;
    }
    @Override
    public User authenticate(String email, String password, HttpServletRequest request) {
        try {
            request.login(email, password);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImp userDetails = (UserDetailsImp) auth.getPrincipal();
            User authenticatedUser = userDetails.getUser();
            authenticatedUser.setPassword(null); // Opcional: no devolver la contraseña
            return authenticatedUser;
        } catch (ServletException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed: " + e.getMessage(), e);
        }
    }
    @Override
    public User getCurrentUserSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof UserDetailsImp)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }

        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        User userSession = userDetails.getUser();
        userSession.setPassword(null); // Optionally clear the password before returning

        return userSession;
    }
    @Override
    public void forgotPassword(String email) {
        try {
            User user = userService.findByEmail(email);

            if (user == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this email does not exist");
            }

            // Generar un token seguro para el restablecimiento de contraseña
            String resetToken = generateResetToken();

            // Calcular la fecha de expiración del token (por ejemplo, 24 horas después de ahora)
            Instant expiryDate = Instant.now().plusSeconds(24 * 60 * 60);

            // Guardar el token en la base de datos
            Token token = new Token(user.getId(), resetToken, expiryDate, Instant.now());
            tokenService.saveToken(token);

            // Preparar el contexto para la plantilla de email (Thymeleaf)
            SendRequest sendRequest = EmailServiceUtil.preparePasswordResetEmail(user, resetToken);

            // Enviar el correo de restablecimiento de contraseña
            emailService.sendMessage(sendRequest, false);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing forgot password request", e);
        }
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int TOKEN_LENGTH = 6;
    private static final Random RANDOM = new Random();

    public static String generateResetToken() {
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            token.append(CHARACTERS.charAt(index));
            if (i < TOKEN_LENGTH - 1) {
                token.append(' ');
            }
        }
        return token.toString();
    }
    @Override
    public ResponseEntity<String> verifyCode(VerifyRequest verifyRequest) {
        try {
            // Verificar el token recibido
            Token token = tokenService.findByToken(verifyRequest.getCode());

            System.out.printf("VER: "+token.toString());

            if (token == null || token.getExpiryDate().isBefore(Instant.now())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid or expired token");
            }

            // Obtener el usuario asociado al token
            Optional<User> userOptional = userService.findById(token.getUserId());

            if (userOptional.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for token");
            }

            // Eliminar el token después de ser verificado
            //tokenService.deleteToken(token.getId());

            // La verificación fue exitosa, devuelve una respuesta exitosa
            return ResponseEntity.ok("Code verified successfully");
        } catch (Exception e) {
            // Manejar cualquier error y devolver una respuesta adecuada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to verify code: " + e.getMessage());
        }
    }
    @Override
    public User changePassword(ChangePasswordRequest changePasswordRequest) {

        String code = changePasswordRequest.getCode();
        String password = changePasswordRequest.getPassword();
        Token token = tokenService.findByToken(code);
        System.out.println("El token es "+ token.toString());
        Optional<User> user = userService.findById(token.getUserId());
        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for token");
        }

        return userService.changePassword(user.get().getId(), password);
    }

}
