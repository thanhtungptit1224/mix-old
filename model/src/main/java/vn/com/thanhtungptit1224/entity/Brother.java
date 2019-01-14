package vn.com.thanhtungptit1224.entity;

import org.springframework.format.annotation.DateTimeFormat;
import vn.com.thanhtungptit1224.validation.Email;
import vn.com.thanhtungptit1224.validation.Length;
import vn.com.thanhtungptit1224.validation.Phone;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class Brother {

    @NotBlank(message = "{Required.message}")
    @Length(min = 5, max = 10)
    private String name;

    @Phone
    private String phone;

    @Email
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
