package com.member.user.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "rpg-member-user")
public interface AuthorityService {

    @PostMapping("/user/getUser")
    String getUser();

    @PostMapping("/user/createUser")
    String createUser();

}
