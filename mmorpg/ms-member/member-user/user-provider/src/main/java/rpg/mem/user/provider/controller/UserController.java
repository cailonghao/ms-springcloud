package rpg.mem.user.provider.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rpg.mem.user.provider.dto.UserDto;

@RequestMapping("/user")
@RestController
public class UserController {


    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/getUser")
    public String getUser(){
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = context.getAuthentication();
        UserDto userDto = new UserDto();
        userDto.setUser_name(authentication.getName());
        userDto.setAuthority((String[]) authentication.getAuthorities().toArray());
        return userDto.toString();
    }

    @PostMapping("/createUser")
    public String createUser(){
        UserDto userDto = new UserDto();
        userDto.setUser_name("一次回眸");
        userDto.setAuthority(new String[]{"Role_user","app_insert"});
        return userDto.toString();
    }

}
