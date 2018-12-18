package vn.com.thanhtungptit1224.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import vn.com.thanhtungptit1224.util.HeaderUtils;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class CustomServerSecurityContextRepository implements ServerSecurityContextRepository {

    private static final String BEARER = "Bearer ";
    private static final Predicate<String> matchBearerLength = authValue -> authValue.length() > BEARER.length();
    private static final Function<String, String> isolateBearerValue = authValue -> authValue.substring(BEARER.length());


//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
//        String authorizationHeader = serverWebExchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring(BEARER.length());
//            Authentication auth = tokenProvider.getAuthentication(token);
//
//            return authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
//        } else {
//            return Mono.empty();
//        }


        return Mono.justOrEmpty(serverWebExchange)
                .map(HeaderUtils::getAuthorizationHeader)
                .filter(StringUtils::isNotEmpty)
                .filter(matchBearerLength)
                .map(isolateBearerValue)
                .filter(StringUtils::isNotEmpty)
                .map(tokenProvider::getAuthentication)
                .filter(Objects::nonNull)
                .map(SecurityContextImpl::new);
    }

}
