package com.mem.auth.client.service;

import com.mem.auth.client.fallback.FeignAuthorityServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "rpg-member-authority", fallback = FeignAuthorityServiceImpl.class)
public interface FeignAuthorityService {


    @PostMapping("/openFeign/getAuthority")
    String getUser(@RequestParam String user_name);

}
