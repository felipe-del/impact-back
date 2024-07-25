package org.impactback.settings.exceptions;

/**
 * @author Isaac F. B. C.
 * @since 7/18/2024 - 2:54 PM
 */

public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     * <p>
     * The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
     * </p>
     *
     * @param message the detail message to be saved.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}