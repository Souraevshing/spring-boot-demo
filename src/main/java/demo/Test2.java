package demo;

import org.springframework.stereotype.Component;

@Component
public class Test2 implements Test{

    Test1 test1 = null;

    public Test2() {

    }

    public Test2(Test1 test1) {
        this.test1 = new Test1();
    }

    public void test() {
        System.out.println("from class test2!");
    }

    @Override
    public void testing() {

    }
}
