package rpg.member.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RpgMemberAuthorityApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpgMemberAuthorityApplication.class, args);
    }
}
