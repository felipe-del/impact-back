package org.impactback.mail.dto;

/**
 * @author Isaac F. B. C.
 * @since 7/11/2024 - 5:40 PM
 */
public class MetaData {

    private String key;

    private String value;

    public MetaData() {
        super();
    }

    public MetaData(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



}