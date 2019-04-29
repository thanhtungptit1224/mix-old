package spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Load AppConfig(Có @Configuration, nơi khai báo các bean với @Bean)
 * Xem có @ComponentScan, @Import, @ImportResource,... không. Nếu có thì sẽ import các bean được load bởi class,
 * package, file xml,... đó vào trước các bean trong AppConfig. Và xử lý các bean đó trước.
 * Các bean này được khởi tạo từ trên xuống dưới trong một class hay trong một package tuỳ theo việc import.
 * - Trong một package: @Service, @Component, @Repository sẽ được ưu tiên hơn @Configuration.
 * - Ví dụ trong TH này: BeanComponent -> BeanService -> AppConfig2(BeanTwo)
 * Sau đó sẽ quay trở lại khởi tạo bean trong AppConfig
 * */
//public class SpringApplication {
//    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        BeanComponent bean = context.getBean(BeanComponent.class);
//        bean.show();
//    }
//}

@SpringBootApplication
public class SpringApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }
}
