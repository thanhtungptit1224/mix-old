package vn.com.thanhtungptit1224.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import vn.com.thanhtungptit1224.service.LoginService;
import vn.com.thanhtungptit1224.util.HeaderUtils;

@RestController
public class TrackingController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/timeout")
    public Long getTimeOut(ServerWebExchange serverWebExchange) {
        String token = HeaderUtils.getToken(serverWebExchange);

        return loginService.getTimeOut(token);
    }

}
