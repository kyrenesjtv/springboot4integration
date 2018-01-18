package me.kyrene.springboot4integration.pojo;

import java.io.Serializable;

/**
 * Created by wanglin on 2018/1/17.
 */
public class Role_permission implements Serializable {
    Role role;
    Permission permission;
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Permission getPermission() {
        return permission;
    }
    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
