package spring;

import org.springframework.stereotype.Service;

@Service
public class BeanService {
    public BeanService() {
        System.out.println("Initial bean service");
    }
}
