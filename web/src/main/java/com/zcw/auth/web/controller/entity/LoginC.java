package com.zcw.auth.web.controller.entity;

/**
 * Created by zhangchengwei on 2016/3/17.
 */
public class LoginC {
    private String name;
    private String token;
    private String projectId;
    private String projectSecret;
    private String equipId;
    private String action;
    private String password;
    private int accountId;

    public LoginC() {
    }

    public LoginC(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public LoginC setName(String name) {
        this.name = name;
        return this;
    }

    public String getToken() {
        return token;
    }

    public LoginC setToken(String token) {
        this.token = token;
        return this;
    }

    public String getAction() {
        return action;
    }

    public LoginC setAction(String action) {
        this.action = action;
        return this;
    }

    public int getAccountId() {
        return accountId;
    }

    public LoginC setAccountId(int accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginC setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEquipId() {
        return equipId;
    }

    public LoginC setEquipId(String equipId) {
        this.equipId = equipId;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public LoginC setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getProjectSecret() {
        return projectSecret;
    }

    public LoginC setProjectSecret(String projectSecret) {
        this.projectSecret = projectSecret;
        return this;
    }
}
