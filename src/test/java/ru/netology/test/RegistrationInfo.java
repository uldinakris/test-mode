package ru.netology.test;

import lombok.Data;

@Data
public class RegistrationInfo {
    private final String login;
    private final String password;

    private String status = "active";
    public void setStatus(String status) {
        this.status = status;
    }
}
