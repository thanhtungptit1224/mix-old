package vn.com.thanhtungptit1224.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Repository
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = passwordEncoder.encode("password");
        if(StringUtils.isEmpty(username)) return Mono.empty();

        if(username.equals("admin")){
            UserLogin sa = new UserLogin("admin", "{bcrypt}" + pass);
            sa.addAuthority(new SimpleGrantedAuthority("ADMIN"));
            return Mono.just(sa);
        }else if (username.equals("user")){
            UserLogin user = new UserLogin("user", "{bcrypt}" + pass);
            user.addAuthority(new SimpleGrantedAuthority("USER"));
            return Mono.just(user);
        }else{
            return Mono.empty();
        }
    }

}
