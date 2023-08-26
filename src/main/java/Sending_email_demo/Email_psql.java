package Sending_email_demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Email_psql implements DataSource {
    @Override
    public String[] getEmail() {

        return new String[]{"abc@yopmail.com", "def@yopmail.com", "ghi@yopmail.com", "jkl@yopmail.com"};

    }
}