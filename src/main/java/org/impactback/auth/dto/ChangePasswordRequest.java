package org.impactback.auth.dto;

/**
 * @author Isaac F. B. C.
 * @since 7/11/2024 - 11:43 PM
 */
public class ChangePasswordRequest {

    private String code;
    private String password;

    // Getters y setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
