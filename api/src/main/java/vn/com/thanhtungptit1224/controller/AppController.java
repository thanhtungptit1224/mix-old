package vn.com.thanhtungptit1224.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.com.thanhtungptit1224.entity.Brother;

import javax.validation.Valid;

@RestController
public class AppController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping(value = "/hi")
    public String hi() {
        return "Hi";
    }

    @PostMapping(value = "/brother")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public Brother brother(@RequestBody @Valid Brother brother) {
        return brother;
    }

}
