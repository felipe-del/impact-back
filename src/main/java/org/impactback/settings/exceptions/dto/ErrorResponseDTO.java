package org.impactback.settings.exceptions.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Isaac F. B. C.
 * @since 7/18/2024 - 4:02 PM
 */
@Setter
@Getter
public class ErrorResponseDTO {
    private String error;

    public ErrorResponseDTO() {
    }

    public ErrorResponseDTO(String error) {
        this.error = error;
    }

}
