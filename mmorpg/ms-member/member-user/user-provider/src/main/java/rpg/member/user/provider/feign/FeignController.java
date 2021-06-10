package rpg.member.user.provider.feign;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rpg.member.user.provider.dto.UserDto;

@RestController
@RequestMapping("/openFeign")
public class FeignController {

    @PostMapping("/getUser")
    public String getUser(@RequestParam String user_name){
        UserDto userDto = new UserDto();
        userDto.setUser_name(user_name);
        userDto.setAuthority(new String[]{"app_open","app_update"});
        return userDto.toString();
    }

}
