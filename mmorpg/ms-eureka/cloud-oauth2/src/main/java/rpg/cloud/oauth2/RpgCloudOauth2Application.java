package rpg.cloud.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RpgCloudOauth2Application {

    public static void main(String[] args) {

        SpringApplication.run(RpgCloudOauth2Application.class, args);
    }

}
