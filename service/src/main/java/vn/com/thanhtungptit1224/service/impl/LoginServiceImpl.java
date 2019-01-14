package vn.com.thanhtungptit1224.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.com.thanhtungptit1224.entity.UserLogin;
import vn.com.thanhtungptit1224.provider.TokenProvider;
import vn.com.thanhtungptit1224.service.LoginService;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private RedisTemplate<String, String> template;

    @Override
    public String login(String username, String password) {
        ValueOperations<String, String> valueOperations = template.opsForValue();

        // If user already login
        if (template.hasKey(username)) {
            return valueOperations.get(username);
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String token = "";

            if (username.equals("admin")) {
                UserLogin sa = new UserLogin("admin", "{bcrypt}" + password);
                sa.addAuthority(new SimpleGrantedAuthority("ADMIN"));

                token = tokenProvider.createToken(sa);
            } else if (username.equals("user")) {
                UserLogin user = new UserLogin("user", "{bcrypt}" + password);
                user.addAuthority(new SimpleGrantedAuthority("USER"));

                token = tokenProvider.createToken(user);
            }

            valueOperations.set(username, token);
            template.expire(username, 300, TimeUnit.SECONDS);

            return token;
        }
    }

    @Override
    public Long getTimeOut(String token) {
        String username = tokenProvider.getUsername(token);
        return template.getExpire(username, TimeUnit.SECONDS);
    }
}
