package spring;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"spring"})
//@Import(value = {AppConfig2.class})
//@ImportResource(locations={
//        "classpath:/com/boraji/tutorial/spring/config/configB.xml"
//})
public class AppConfig {

//    @Bean("beanTwo")
//    public BeanTwo getBeanTwo() {
//        return new BeanTwo();
//    }

    @Bean("beanThree")
    public BeanThree getBeanThree() {
        return new BeanThree();
    }

    @Bean("beanOne")
    @Lazy
    public BeanOne getBeanOne() {
        return new BeanOne();
    }

//    @Bean("beanBeanComponent")
//    public BeanComponent getBeanBeanComponent() {
//        return new BeanComponent(getBeanTwo());
//    }
}
