package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import spring.BeanOne;
import spring.BeanTwo;

@Component
public class BeanComponent {


//    private BeanOne one;
//
//    public Test() {
//        System.out.println("initialized bean test");
//    }
//
//    @Autowired
//    public void setOne(BeanOne one) {
//        this.one = one;
//        System.out.println("Inject by type------------------------------------");
//    }
//
//    public void show() {
//        one.doSt();
//    }


    private BeanTwo two;

    public BeanComponent() {
        System.out.println("initialized bean test");
    }

    @Autowired
    public BeanComponent(BeanTwo two) {
        this.two = two;
        System.out.println("Inject by constructor------------------------------------");
    }


    public void setTwo(BeanTwo two) {
        this.two = two;
        System.out.println("Inject by type------------------------------------");
    }

    public void show() {
        two.doSt();
    }
}
