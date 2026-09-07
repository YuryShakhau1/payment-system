package by.shakhau.ps.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "by.shakhau")
public class PaymentSystemApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentSystemApiGatewayApplication.class, args);
    }

}
