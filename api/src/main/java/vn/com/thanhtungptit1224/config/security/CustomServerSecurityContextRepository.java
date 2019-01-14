package vn.com.thanhtungptit1224.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import vn.com.thanhtungptit1224.provider.TokenProvider;
import vn.com.thanhtungptit1224.util.HeaderUtils;

@Component
public class CustomServerSecurityContextRepository implements ServerSecurityContextRepository {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private RedisTemplate<String, String> template;

    @Override
    public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        String token = HeaderUtils.getToken(serverWebExchange);

        if (!token.equals("")) {
            /*If don't exists key(username) in redis, not yet login or already logout*/
            String username = tokenProvider.getUsername(token);
            if (!template.hasKey(username))
                return Mono.empty();

            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return Mono.just(new SecurityContextImpl(authentication));
        } else {
            return Mono.empty();
        }


//        return Mono.justOrEmpty(serverWebExchange)
//                .map(HeaderUtils::getAuthorizationHeader)
//                .filter(StringUtils::isNotEmpty)
//                .map(token -> {
//                    Authentication authentication = tokenProvider.getAuthentication(token);
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    return authentication;
//                })
//                .filter(Objects::nonNull)
//                .map(SecurityContextImpl::new);

    }

}
