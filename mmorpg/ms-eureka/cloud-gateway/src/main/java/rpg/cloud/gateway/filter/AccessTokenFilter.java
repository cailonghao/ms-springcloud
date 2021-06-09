package rpg.cloud.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import rpg.cloud.gateway.dto.PayloadDto;
import rpg.cloud.gateway.util.JwtUtil;
import rpg.cloud.gateway.util.RsaUtil;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AccessTokenFilter implements GlobalFilter, Ordered {
    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().getPath();
        String accessToken = request.getHeaders().getFirst("Authorization");
        if (StringUtils.isEmpty(accessToken) || !accessToken.startsWith("Bearer")) {
            return chain.filter(exchange);
        }
        accessToken = accessToken.replace("Bearer", Strings.EMPTY);
        ObjectMapper mapper = new ObjectMapper();
        try {
            PayloadDto payloadDto = JwtUtil.verifyJwt(accessToken);
            Map<String, Object> map = new HashMap<>();
            assert payloadDto != null;
            map.put("authority", payloadDto.getAuthorities());
            map.put("user_name", payloadDto.getUser_name());
            String json = mapper.writeValueAsString(map);
            String sign = RsaUtil.signature(json);
            ServerHttpRequest newRequest = request.mutate()
                    .header("sign", sign)
                    .header("userInfo", json)
                    .build();
            return chain.filter(exchange.mutate().request(newRequest).build());
        } catch (Exception e) {
            log.info("解析失败 {}",e.getMessage());
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
