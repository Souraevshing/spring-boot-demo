package demo;

//Annotation based configuration

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("demo")
public class Test4 implements Test {

    @Autowired
    public void test(@Qualifier  int n) {
        System.out.println("from class test3");
    }

    @Override
    public void testing() {

    }
}
