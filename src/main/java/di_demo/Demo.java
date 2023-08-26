package di_demo;


class A {

}

class B {

}

class C {

}
public class Demo {

    private A a;
    private B b;
    private C c;

    public Demo() {
    }

    public Demo(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
