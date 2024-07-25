package org.impactback.settings.exceptions.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Isaac F. B. C.
 * @since 7/18/2024 - 4:03 PM
 */

@Setter
@Getter
public class SuccessResponseDTO {
    private String message;

    public SuccessResponseDTO() {
    }

    public SuccessResponseDTO(String message) {
        this.message = message;
    }

}
