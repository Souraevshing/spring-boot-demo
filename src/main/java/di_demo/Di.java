package di_demo;

public class Di {

    private DiDemo demo;

    public void DiDemo(DiDemo demo) {
        this.demo = demo;
    }

    public void test(String s) {
        this.demo.di(s);
    }

}
