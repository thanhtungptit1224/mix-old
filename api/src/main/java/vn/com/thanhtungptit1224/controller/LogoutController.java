package vn.com.thanhtungptit1224.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import vn.com.thanhtungptit1224.service.LogoutService;
import vn.com.thanhtungptit1224.util.HeaderUtils;

@RestController
public class LogoutController {

    @Autowired
    private LogoutService logoutService;

    @GetMapping(value = "/logout")
    public ResponseEntity logout(ServerWebExchange serverWebExchange) {
        String token = HeaderUtils.getToken(serverWebExchange);
        logoutService.logout(token);

        return ResponseEntity.ok().build();
    }

}
