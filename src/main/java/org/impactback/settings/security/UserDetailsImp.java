package org.impactback.settings.security;

import lombok.Getter;
import org.impactback.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementation of the {@link UserDetails} interface for representing user-specific data.
 * <p>
 * This class adapts the {@link User} entity to the {@link UserDetails} interface used by Spring Security
 * for authentication and authorization. It provides user details such as username, password, and roles.
 * </p>
 *
 * @author Isaac F. B. C.
 * @since 6/27/2024 - 10:43 PM
 */
@Getter
public class UserDetailsImp implements UserDetails {
    private final User user;

    /**
     * Constructs a {@link UserDetailsImp} with the specified {@link User}.
     * <p>
     * This constructor is used to initialize the UserDetails implementation with user-specific data.
     * </p>
     *
     * @param user the {@link User} instance containing user-specific data.
     */
    public UserDetailsImp(User user) {
        this.user = user;
    }

    /**
     * Returns the authorities granted to the user.
     * <p>
     * In this implementation, it retrieves the role of the user and wraps it in a {@link SimpleGrantedAuthority}
     * object, which is used by Spring Security to handle authorization.
     * </p>
     *
     * @return a {@link Collection} of {@link GrantedAuthority} representing the user's roles.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return authorities;
    }

    /**
     * Returns the password used to authenticate the user.
     * <p>
     * This implementation returns the password from the {@link User} entity.
     * </p>
     *
     * @return the password of the user.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the username used to authenticate the user.
     * <p>
     * This implementation returns the email from the {@link User} entity, which is used as the username.
     * </p>
     *
     * @return the username (email) of the user.
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Indicates whether the user's account has expired.
     * <p>
     * This implementation always returns {@code true}, meaning the account is not expired.
     * </p>
     *
     * @return {@code true} if the account is not expired, {@code false} otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is locked.
     * <p>
     * This implementation always returns {@code true}, meaning the account is not locked.
     * </p>
     *
     * @return {@code true} if the account is not locked, {@code false} otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     * <p>
     * This implementation always returns {@code true}, meaning the credentials are not expired.
     * </p>
     *
     * @return {@code true} if the credentials are not expired, {@code false} otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or not.
     * <p>
     * This implementation always returns {@code true}, meaning the user is enabled.
     * </p>
     *
     * @return {@code true} if the user is enabled, {@code false} otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
