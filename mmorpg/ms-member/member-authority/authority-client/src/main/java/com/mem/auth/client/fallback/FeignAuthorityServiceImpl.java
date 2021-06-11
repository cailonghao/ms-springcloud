package com.mem.auth.client.fallback;

import com.mem.auth.client.service.FeignAuthorityService;
import org.springframework.stereotype.Service;

@Service
public class FeignAuthorityServiceImpl implements FeignAuthorityService {

    @Override
    public String getUser(String user_name) {
        return "openFeign fail ...";
    }
}
