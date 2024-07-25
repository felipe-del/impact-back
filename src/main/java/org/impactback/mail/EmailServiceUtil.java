package org.impactback.mail;

import org.impactback.mail.dto.MetaData;
import org.impactback.mail.dto.SendRequest;
import org.impactback.user.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Isaac F. B. C.
 * @since 7/11/2024 - 7:56 PM
 */
public class EmailServiceUtil {

    public static SendRequest prepareWelcomeEmail(User user) {
        List<MetaData> metaData = new ArrayList<>();
        metaData.add(new MetaData("name", user.getName()));
        return new SendRequest(user.getEmail(), "Bienvenido a nuestra plataforma", "welcome-email", metaData);
    }

    public static SendRequest preparePasswordResetEmail(User user, String resetToken) {
        List<MetaData> metaData = new ArrayList<>();
        metaData.add(new MetaData("name", user.getName()));
        metaData.add(new MetaData("resetCode", resetToken));
        return new SendRequest(user.getEmail(), "Restablecimiento de contraseña", "reset-password-email", metaData);
    }


}