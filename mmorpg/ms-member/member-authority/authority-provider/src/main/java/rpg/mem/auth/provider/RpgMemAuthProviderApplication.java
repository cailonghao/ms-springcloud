package rpg.mem.auth.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {
        "com.member.user.client"
})
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "rpg.mem.auth.provider",
        "com.member.user.client"})
public class RpgMemAuthProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpgMemAuthProviderApplication.class, args);
    }
}
