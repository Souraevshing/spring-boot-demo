package Sending_email_demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        EmailService emailService = context.getBean(EmailService.class);
        emailService.send();

    }

}
