package vn.com.thanhtungptit1224.service;

public interface LoginService {

    String login(String username, String password);

    Long getTimeOut(String token);

}
