package rpg.mem.user.provider.feign;

import com.mem.auth.client.service.FeignAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rpg.mem.user.provider.dto.UserDto;

@RestController
@RequestMapping("/openFeign")
public class FeignController {


    @Autowired
    private FeignAuthorityService feignAuthorityService;

    @PostMapping("/getUser")
    public String getUser(@RequestParam String user_name) {
        UserDto userDto = new UserDto();
        userDto.setUser_name(user_name);
        String authority = feignAuthorityService.getUser(user_name);
        String[] str = authority.split(",");
        userDto.setAuthority(str);
        return userDto.toString();
    }

}
