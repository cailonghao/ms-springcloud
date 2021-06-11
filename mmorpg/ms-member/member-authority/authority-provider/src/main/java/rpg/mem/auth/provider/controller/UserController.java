package rpg.mem.auth.provider.controller;

import com.member.user.client.service.FeignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    FeignUserService feignUserService;


    @PostMapping("/getUser")
    String getUser(){
        return feignUserService.getUser("cainiao");
    }



}
