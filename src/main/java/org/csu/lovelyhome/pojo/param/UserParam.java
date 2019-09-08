package org.csu.lovelyhome.pojo.param;

import javax.validation.constraints.NotNull;

public class UserParam {
    @NotNull(message = "账号不能为空")
    private String phone;

    @NotNull(message = "密码不能为空")
    private String password;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public @NotNull(message = "账号不能为空") String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public @NotNull(message = "密码不能为空") String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
