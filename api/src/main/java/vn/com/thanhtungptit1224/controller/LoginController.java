package vn.com.thanhtungptit1224.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.thanhtungptit1224.service.LoginService;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/loginHandler")
    public String loginHandler(@RequestParam String username, @RequestParam String password) {
        return loginService.login(username, password);
    }

}
