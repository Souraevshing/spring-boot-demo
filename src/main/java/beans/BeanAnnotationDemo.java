package beans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class Student {

    private Address address;

    public Student(Address address) {
        this.address = address;
    }

    void display() {
        System.out.println("from class Student");
    }

    void initialize() {
        System.out.println("initialization done!");
    }

    void destroyed() {
        System.out.println("destroyed!");
    }

}

class Address {

    void display() {
        System.out.println("from class Address");
    }

}

@Configuration
class TestBean {

    @Bean
    public Address address() {
        return new Address();
    }

    //two lifecycle methods initMethod and destroyMethod
    //we pass method name as string to these parameters
    @Bean(initMethod = "initialize", destroyMethod = "destroyed")
    public Student student() {
        return new Student(address());
    }

}

public class BeanAnnotationDemo {

    //annotating with @Bean to create obj of Student class
    //creating ioc container inside try block will trigger the lifecycle methods
    public static void main(String[] args) {
        try(var context = new AnnotationConfigApplicationContext(TestBean.class)) {
            Student student = context.getBean(Student.class);
            student.display();
            String[] beans = context.getBeanDefinitionNames();
            //displaying all bean names
            for (String bean : beans) {
                System.out.println(bean + "\n");
            }
        }
    }
}
