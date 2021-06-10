package com.member.user.client.service;

import com.member.user.client.fallback.FeignUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "rpg-member-user",fallback = FeignUserServiceImpl.class)
public interface FeignUserService {


    @PostMapping("/openFeign/getUser")
    String getUser(@RequestParam String user_name);

}
