package org.impactback.settings.security;

import org.impactback.user.entity.User;
import org.impactback.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link UserDetailsService} interface for loading user-specific data.
 * <p>
 * This service is used by Spring Security to retrieve user details for authentication and authorization.
 * It fetches user information from the {@link UserRepository} and constructs a {@link UserDetails} object.
 * </p>
 *
 * @author Isaac F. B. C.
 * @since 6/27/2024 - 10:44 PM
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructs a {@link UserDetailsServiceImpl} with the specified {@link UserRepository}.
     * <p>
     * This constructor is used for dependency injection by Spring.
     * </p>
     *
     * @param userRepository the {@link UserRepository} instance used to fetch user data.
     */
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads user-specific data by username.
     * <p>
     * This method retrieves user information from the {@link UserRepository} based on the provided username (email).
     * If the user is not found, a {@link UsernameNotFoundException} is thrown.
     * </p>
     *
     * @param username the username (email) of the user to retrieve.
     * @return a {@link UserDetails} object containing user details.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return new UserDetailsImp(user);
    }
}
