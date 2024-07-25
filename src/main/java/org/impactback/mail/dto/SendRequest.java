package org.impactback.mail.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Isaac F. B. C.
 * @since 7/11/2024 - 5:41 PM
 */
@Setter
@Getter
public class SendRequest {

    private String to;

    private String subject;

    private String template;

    private List<MetaData> metaData;

    public SendRequest() {
        super();
    }

    public SendRequest(String to, String subject, String template, List<MetaData> metaData) {
        super();
        this.to = to;
        this.subject = subject;
        this.template = template;
        this.metaData = metaData;
    }

}