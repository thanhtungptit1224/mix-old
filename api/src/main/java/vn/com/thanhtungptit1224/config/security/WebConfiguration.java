package vn.com.thanhtungptit1224.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.LogoutWebFilter;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Configuration
@EnableWebFluxSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfiguration {

//    @Autowired
//    private ReactiveAuthenticationManager authenticationManager;

    @Autowired
    private CustomServerSecurityContextRepository securityContextRepository;

    /**
     * Equivalent using WebSecurity Http to ignore
     */
    private static final String[] AUTH_WHITELIST = {
            "/resources/**",
            "/webjars/**",
            "/authorize/**",
            "/favicon.ico",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/configuration/**",
            "/webjars/**",
            "/loginPage",
            "/loginHandler"
    };

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        /**We are stateless, so won't need to logout and won't need CSRF protection*/
        http
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .logout().disable();

//        http
//                .formLogin()
//                .loginPage("/loginPage");
//                .authenticationSuccessHandler(new LoginSuccessHandler("/home"))
//                .authenticationFailureHandler(new RedirectServerAuthenticationFailureHandler(""));

//        http
//                .addFilterAt(loginWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
//                .addFilterAt(logoutWebFilter(), SecurityWebFiltersOrder.LOGOUT);

        http
//                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers("/home", "/hello").hasAuthority("USER")
                .pathMatchers("/hi").hasAuthority("ADMIN")
                .pathMatchers(AUTH_WHITELIST).permitAll()
                .anyExchange().authenticated();

//        http
//                .logout()
//                .logoutUrl("logout")
//                .logoutHandler(new SecurityContextServerLogoutHandler())
//                .logoutSuccessHandler(new RedirectServerLogoutSuccessHandler());


//        http
//                .exceptionHandling()
//                .authenticationEntryPoint((exchange, exception) -> Mono.error(exception))
//                .accessDeniedHandler((exchange, exception) -> Mono.error(exception))
//                .accessDeniedHandler((exchange, exception) -> Mono.error(exception));


        return http.build();
    }

//    private AuthenticationWebFilter loginWebFilter() {
//        AuthenticationWebFilter filter = new AuthenticationWebFilter(authenticationManager);
//
//        filter.setAuthenticationConverter(new JwtConverter());
//        filter.setAuthenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/home"));
//        filter.setAuthenticationFailureHandler(new RedirectServerAuthenticationFailureHandler("/login"));
//        filter.setRequiresAuthenticationMatcher(new HeaderExchangeMatcher());
//        filter.setSecurityContextRepository(securityContextRepository);
//
//        return filter;
//    }

    private LogoutWebFilter logoutWebFilter() {
        LogoutWebFilter logoutWebFilter = new LogoutWebFilter();

        SecurityContextServerLogoutHandler logoutHandler = new SecurityContextServerLogoutHandler();
        logoutHandler.setSecurityContextRepository(securityContextRepository);

        RedirectServerLogoutSuccessHandler logoutSuccessHandler = new RedirectServerLogoutSuccessHandler();
        logoutSuccessHandler.setLogoutSuccessUrl(URI.create("/"));

        logoutWebFilter.setLogoutHandler(logoutHandler);
        logoutWebFilter.setLogoutSuccessHandler(logoutSuccessHandler);
        logoutWebFilter.setRequiresLogoutMatcher(ServerWebExchangeMatchers.pathMatchers(HttpMethod.GET, "/logout"));

        return logoutWebFilter;
    }

    private class HeaderExchangeMatcher implements ServerWebExchangeMatcher {
        @Override
        public Mono<ServerWebExchangeMatcher.MatchResult> matches(final ServerWebExchange exchange) {
            Mono<ServerHttpRequest> request = Mono.just(exchange).map(ServerWebExchange::getRequest);

            /* Check for header "Authorization" */
            return request.map(ServerHttpRequest::getHeaders)
                    .filter(h -> h.containsKey(HttpHeaders.AUTHORIZATION))
                    .flatMap($ -> MatchResult.match())
                    .switchIfEmpty(MatchResult.notMatch());
        }
    }
}
