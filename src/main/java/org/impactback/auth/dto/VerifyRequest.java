package org.impactback.auth.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Isaac F. B. C.
 * @since 7/11/2024 - 10:58 PM
 */
@Setter
@Getter
public class VerifyRequest {
    private String code;

    // Constructor, getters y setters
    public VerifyRequest() {}

    public VerifyRequest(String code) {
        this.code = code;
    }

}
