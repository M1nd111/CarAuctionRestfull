package spring.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableScheduling
public class CarAuctionRestfullApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(CarAuctionRestfullApplication.class, args);
    }
}
