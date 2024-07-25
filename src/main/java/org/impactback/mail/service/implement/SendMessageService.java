package org.impactback.mail.service.implement;

import org.impactback.mail.dto.MetaData;
import org.impactback.mail.dto.SendRequest;
import org.impactback.mail.service.ISendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Isaac F. B. C.
 * @since 6/28/2024 - 11:26 AM
 */
@Service
public class SendMessageService implements ISendMessageService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private static final String FROM = "t86043230@gmail.com";

    @Autowired
    public SendMessageService(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.mailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public ResponseEntity<String> sendMessage(SendRequest sendRequest, boolean async) {
        try {
            if (validated(sendRequest.getTo())) {
                String messageContent = buildMessage(sendRequest);
                MimeMessagePreparator preparation = mimeMessage -> {
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                    helper.setFrom(FROM);
                    helper.setTo(sendRequest.getTo());
                    helper.setSubject(sendRequest.getSubject());
                    helper.setText(messageContent, true);
                };

                if (async) {
                    sendAsync(preparation);
                } else {
                    sendSync(preparation);
                }

                return ResponseEntity.status(HttpStatus.CREATED).body("Enviado exitosamente!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Correo electrónico inválido!");
            }

        } catch (NotAcceptableStatusException e1) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e1.getReason());
        }
    }

    private String buildMessage(SendRequest sendRequest) throws NotAcceptableStatusException {
        try {
            Context context = new Context();
            // Agregar variables al contexto de Thymeleaf
            for (MetaData meta : sendRequest.getMetaData()) {
                context.setVariable(meta.getKey(), meta.getValue());
            }

            // Procesar la plantilla Thymeleaf
            return templateEngine.process(sendRequest.getTemplate(), context);
        } catch (TemplateInputException e) {
            throw new NotAcceptableStatusException("Error al procesar la plantilla Thymeleaf: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Boolean validated(String email) {
        // Validar el formato del correo electrónico
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    @Async
    protected void sendAsync(MimeMessagePreparator preparator) {
        mailSender.send(preparator);
    }

    private void sendSync(MimeMessagePreparator preparator) {
        mailSender.send(preparator);
    }
}
