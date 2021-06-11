package rpg.mem.auth.provider.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import rpg.mem.auth.provider.dto.UserDto;
import rpg.mem.auth.provider.util.RsaUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("token filter...");
        String sign = request.getHeader("sign");
        String userInfo = request.getHeader("userInfo");
        if (StringUtils.isEmpty(userInfo) || StringUtils.isEmpty(userInfo)) {
            filterChain.doFilter(request, response);
        } else {
            //校验sign
            Boolean isOk = RsaUtil.verify(userInfo, sign);
            if (!isOk) {
                filterChain.doFilter(request, response);
                return;
            }
            log.info("sign = {}", sign);
            log.info("userInfo = {}", userInfo);
            ObjectMapper mapper = new ObjectMapper();
            UserDto userDto = mapper.readValue(userInfo, UserDto.class);
            log.info("userDto = {}", userDto);
            //写入线程
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authentication =
                    new TestingAuthenticationToken(userDto.getUser_name(), "", userDto.getAuthority());
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
            filterChain.doFilter(request, response);
        }

    }
}
