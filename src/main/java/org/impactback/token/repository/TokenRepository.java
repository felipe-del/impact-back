package org.impactback.token.repository;

import org.impactback.token.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Isaac F. B. C.
 * @since 7/11/2024 - 5:16 PM
 */
@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findTokenByToken(String token);
}
