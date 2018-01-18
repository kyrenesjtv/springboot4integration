package me.kyrene.springboot4integration.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wanglin on 2018/1/17.
 */
public class UserInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    Long userid;
    String username;
    String password;
    List<User_role> user_roles;
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<User_role> getUser_roles() {
        return user_roles;
    }
    public void setUser_roles(List<User_role> user_roles) {
        this.user_roles = user_roles;
    }
}
