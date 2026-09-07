package by.shakhau.ps.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
@EnableRetry
@ComponentScan(basePackages = "by.shakhau")
public class PaymentSystemOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentSystemOrderServiceApplication.class, args);
    }
}
