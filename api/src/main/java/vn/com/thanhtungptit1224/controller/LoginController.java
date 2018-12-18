package vn.com.thanhtungptit1224.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.thanhtungptit1224.config.TokenProvider;
import vn.com.thanhtungptit1224.config.UserLogin;

@RestController
public class LoginController {

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping(value = "/loginHandler")
    public String loginHandler(@RequestParam String username, @RequestParam String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = passwordEncoder.encode("password");
        if (StringUtils.isEmpty(username))
            return "Login Fail";

        if (username.equals("admin")) {
            UserLogin sa = new UserLogin("admin", "{bcrypt}" + pass);
            sa.addAuthority(new SimpleGrantedAuthority("ADMIN"));

            return tokenProvider.createToken(sa);
        } else if (username.equals("user")) {
            UserLogin user = new UserLogin("user", "{bcrypt}" + pass);
            user.addAuthority(new SimpleGrantedAuthority("USER"));
            return tokenProvider.createToken(user);
        }

        return "Login Success";
    }

}
