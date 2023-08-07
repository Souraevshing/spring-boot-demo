package com.annotations.springannotation.SourceProperty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SourceProperty {

    @Value("${user.name}")
    private String userName;

    @Value("${user.email}")
    private String emailId;

    @Value("${user.password}")
    private String password;

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }
}
