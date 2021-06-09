package rpg.cloud.gateway.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 服务降级
 */
@RestController
public class UserFallback {

    @RequestMapping("/fallback")
    public Mono<String> hello() {
        return Mono.just("Welcome to reactive world ~");
    }
}
