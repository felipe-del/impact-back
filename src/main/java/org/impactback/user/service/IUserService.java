package org.impactback.user.service;

import org.impactback.user.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Isaac F. B. C.
 * @since 7/11/2024 - 8:48 PM
 */
public interface IUserService {
    List<User> findAll();

    User saveUser(User user) throws Exception;

    User findByEmail(String email);

    Optional<User> findById(int id);
    User changePassword(int userId, String newPassword);
}
