package vn.com.thanhtungptit1224.util;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

public class HeaderUtils {

    private static final String BEARER = "Bearer ";

    public static String getHeader(ServerWebExchange serverWebExchange, String header) {
        String token = serverWebExchange.getRequest()
                .getHeaders()
                .getFirst(header);

        return StringUtils.isEmpty(token) ? Strings.EMPTY : token;
    }

    public static String getToken(ServerWebExchange serverWebExchange) {
        String header = getHeader(serverWebExchange, HttpHeaders.AUTHORIZATION);
        String token = "";

        if (header.startsWith("Bearer "))
            token = header.substring(BEARER.length());

        return token;
    }

}
