package com.minakov.dto;

import com.minakov.validation.annotations.PasswordsMatch;
import com.minakov.validation.annotations.ValidPassword;
import com.minakov.validation.annotations.ValidPhone;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * This class is Data Transfer Object.
 * It is used during registration new user.
 * UserDto can be validated with phone and password
 */
@PasswordsMatch
public class UserDto {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    @ValidPhone
    private String phone;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;
    private String confirmPassword;

    public UserDto() {
    }

    public UserDto(String name, String login, String phone, String password, String confirmPassword) {
        this.name = name;
        this.login = login;
        this.phone = phone;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(name, userDto.name) && Objects.equals(login, userDto.login) &&
                Objects.equals(phone, userDto.phone) && Objects.equals(password, userDto.password) &&
                Objects.equals(confirmPassword, userDto.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, phone, password, confirmPassword);
    }
}
