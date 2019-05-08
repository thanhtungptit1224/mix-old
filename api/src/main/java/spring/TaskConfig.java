package spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
//@EnableScheduling
public class TaskConfig {

    /**
     * second minute hour day day-of-month day-of-week
     * * : it is used to specify that event should happen for every time unit
     * ? : Giống * nhưng thường sử dụng cho day. Ví dụ: ngày 25 nhưng phải là thứ 5
     * - : Khoảng từ đâu đến đâu
     * , : Chỉ có thể là các giá trị này
     * a/b : Bắt đầu là a, tăng đều bởi b
     * L : Last
     * # : Sử dụng cho day-of-week. Ví dụ: 6#3 là ngày thứ 6 thứ 3 của tháng
     * */
    @Scheduled(cron = "0/5 * * * * *")
    public void show() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println("-------------------- Hello Brother ----------------------");
    }

}
