package me.kyrene.springboot4integration.DAO.shiro;


import me.kyrene.springboot4integration.pojo.shiro.User;

import java.util.List;

/**
 * Created by wanglin on 2018/1/18.
 */
public interface UserMapper {
    List<User> getusers();
    User getUserByid(long userid);
    User getUserByusername(String username);
    void deleteuser(long userid);
    void deleteuserrole(long userid, long roleid);
    void adduserrole(long userid, long roleid);
    void adduser(User user);
    void updateuser(User user);
    void deleteuseroles(long uid);
}
