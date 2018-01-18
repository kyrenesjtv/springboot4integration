package me.kyrene.springboot4integration.pojo.shiro;

import me.kyrene.springboot4integration.pojo.User;

import java.io.Serializable;

/**
 * Created by wanglin on 2018/1/17.
 */
public class User_role implements Serializable {
    User user;
    Role role;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
