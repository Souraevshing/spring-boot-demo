package Sending_email_demo;

import org.springframework.stereotype.Component;

@Component
public class EmailService {

    private DataSource dataSource;

    //we don't need to use
    public EmailService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    void send() {
        String[] emails = dataSource.getEmail();
        for (String email : emails) {
            System.out.println(email+"\n");
        }
    }

}
