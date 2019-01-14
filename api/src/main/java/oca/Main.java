package oca;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    int count = 0;

    public void incre() {
        synchronized (this) {
            System.out.println(++count);
        }
//        System.out.println(++count);
    }

    public static void main(String[] args) {
        ExecutorService service = null;


        try {
            service = Executors.newScheduledThreadPool(4);
            Main main = new Main();
            for (int i = 0; i < 10; i++) {
                service.submit(() -> main.incre());
            }
        } catch (Exception e) {
            if (service != null) {
                service.shutdown();
            }
        }

        if (service != null) {
            service.shutdown();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof A) {
            A a = (A) obj;
        }
        return super.equals(obj);
    }
}

class A {

}


