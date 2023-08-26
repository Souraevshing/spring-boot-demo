package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Java based configuration

@Configuration
public class Test1 {


    public Test1() {

    }
    @Bean
    public void test() {
        System.out.println("from class test1!");
    }

}
