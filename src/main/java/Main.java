import demo.Test1;
import demo.Test2;
import di_demo.DiDemo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    //with dependency injection, spring ioc container will be responsible for creating objects as needed.
    public static void main(String[] args) {
        System.out.println("Demo for Spring 6\n".toUpperCase());

        //create ioc container
        ApplicationContext context = new AnnotationConfigApplicationContext(Test1.class);

        //retrieve bean from ioc container
        context.getBean(Test1.class);
    }

    //without dependency injection, we have to manually create objects.
    /** public static void main(String[] args) {
        DiDemo demo = new DiDemo();
        demo.di("testing");
    } */


}
