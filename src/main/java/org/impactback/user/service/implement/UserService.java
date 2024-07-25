package org.impactback.user.service.implement;

import org.impactback.mail.EmailServiceUtil;
import org.impactback.mail.dto.SendRequest;
import org.impactback.mail.service.implement.SendMessageService;
import org.impactback.user.entity.User;
import org.impactback.user.repository.UserRepository;
import org.impactback.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * @author Isaac F. B. C.
 * @since 6/27/2024 - 10:47 PM
 */
@Service("userService")
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final SendMessageService emailService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository, SendMessageService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    @Override
    public User saveUser(User user) throws Exception {
        // Encriptar la contraseña con BCrypt
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        user.setRole("TEACHER");
        user.setState((byte) 0);

        User savedUser = this.userRepository.save(user);
        savedUser.setPassword(null);

        // Preparar y enviar correo de bienvenida
        SendRequest sendRequest = EmailServiceUtil.prepareWelcomeEmail(savedUser);
        emailService.sendMessage(sendRequest, true);

        return savedUser;
    }
    @Override
    public User changePassword(int userId, String newPassword) {
        // Find the user by userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Update user's password
        user.setPassword(passwordEncoder.encode(newPassword)); // Encode the password before saving

        // Save the updated user entity
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

}
