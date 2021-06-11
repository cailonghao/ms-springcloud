package rpg.mem.user.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {
        "com.mem.auth.client"
})
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "rpg.mem.user.provider",
        "com.mem.auth.client"})
public class RpgMemberUserApplication {

    public static void main(String[] args) {

        SpringApplication.run(RpgMemberUserApplication.class, args);
    }

}
