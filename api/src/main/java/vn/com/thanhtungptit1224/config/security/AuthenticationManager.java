package vn.com.thanhtungptit1224.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Component;

@Component
//public class AuthenticationManager implements ReactiveAuthenticationManager {
//
//    @Override
//    public Mono<Authentication> authenticate(Authentication authentication) {
////        String authToken = authentication.getCredentials().toString();
////
////        String username;
////        try {
////            username = jwtUtil.getUsernameFromToken(authToken);
////        } catch (Exception e) {
////            username = null;
////        }
////        if (username != null && jwtUtil.validateToken(authToken)) {
////            Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
////            List<String> rolesMap = claims.get("role", List.class);
////            List<Role> roles = new ArrayList<>();
////            for (String rolemap : rolesMap) {
////                roles.add(Role.valueOf(rolemap));
////            }
////            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
////                    username,
////                    null,
////                    roles.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList())
////            );
////            return Mono.just(auth);
////        } else {
////            return Mono.empty();
////        }
//
//        return Mono.empty();
//    }
//}

public class AuthenticationManager extends UserDetailsRepositoryReactiveAuthenticationManager {

    @Autowired
    public AuthenticationManager(ReactiveUserDetailsService reactiveUserDetailsService) {
        super(reactiveUserDetailsService);
    }

}


