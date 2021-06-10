package com.member.user.client.fallback;

import com.member.user.client.service.FeignUserService;
import org.springframework.stereotype.Service;

@Service
public class FeignUserServiceImpl implements FeignUserService {
    @Override
    public String getUser(String user_name) {
        return "openFeign fail ...";
    }
}
