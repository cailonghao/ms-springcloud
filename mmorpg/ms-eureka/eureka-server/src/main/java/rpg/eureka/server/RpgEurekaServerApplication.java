package rpg.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RpgEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpgEurekaServerApplication.class, args);
    }

}
