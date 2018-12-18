package vn.com.thanhtungptit1224.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import vn.com.thanhtungptit1224.util.HeaderUtils;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class JwtConverter implements Function<ServerWebExchange, Mono<Authentication>> {

    private static final String BEARER = "Bearer ";
    private static final Predicate<String> matchBearerLength = authValue -> authValue.length() > BEARER.length();
    private static final Function<String, String> isolateBearerValue = authValue -> authValue.substring(BEARER.length());

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public Mono<Authentication> apply(ServerWebExchange serverWebExchange) {
        return Mono.justOrEmpty(serverWebExchange)
                .map(HeaderUtils::getAuthorizationHeader)
                .filter(Objects::nonNull)
                .filter(matchBearerLength)
                .map(isolateBearerValue)
                .filter(StringUtils::isNotEmpty)
                .map(tokenProvider::getAuthentication)
                .filter(Objects::nonNull);
    }

}

