package spring.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CarAuctionRestfullApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(CarAuctionRestfullApplication.class, args);
    }
}
