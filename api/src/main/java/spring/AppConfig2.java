package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.BeanTwo;

@Configuration
public class AppConfig2 {
    @Bean("beanTwo")
    public BeanTwo getBeanTwo() {
        return new BeanTwo();
    }
}
