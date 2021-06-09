package rpg.member.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {


    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
