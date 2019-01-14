package vn.com.thanhtungptit1224.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.com.thanhtungptit1224.provider.TokenProvider;
import vn.com.thanhtungptit1224.service.LogoutService;

@Service
public class LogoutServiceImpl implements LogoutService {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private RedisTemplate<String, String> template;

    @Override
    public boolean logout(String token) {
        String username = tokenProvider.getUsername(token);
        return template.delete(username);
    }

}
