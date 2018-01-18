package me.kyrene.springboot4integration.service.shiro.impl;

import com.github.pagehelper.PageHelper;
import me.kyrene.springboot4integration.DAO.shiro.RoleMapper;
import me.kyrene.springboot4integration.DAO.shiro.UserMapper;
import me.kyrene.springboot4integration.pojo.shiro.Role;
import me.kyrene.springboot4integration.pojo.shiro.Role_permission;
import me.kyrene.springboot4integration.pojo.shiro.User;
import me.kyrene.springboot4integration.pojo.shiro.User_role;
import me.kyrene.springboot4integration.service.shiro.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wanglin on 2018/1/18.
 */
@Service("userservice")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper usermapper;

    @Autowired
    RoleMapper rolemapper;

    public User createUser(User user) {
        usermapper.adduser(user);
        return user;
    }

    public void changePassword(Long userId, String newPassword) {
        User u=usermapper.getUserByid(userId);
        u.setPassword(newPassword);
        usermapper.updateuser(u);
    }

    public void correlationRoles(Long userId, Long... roleIds) {
        for(Long roleid:roleIds){
            usermapper.adduserrole(userId, roleid);
        }
    }

    public void uncorrelationRoles(Long userId, Long... roleIds) {
        for(Long roleid:roleIds){
            usermapper.deleteuserrole(userId, roleid);
        }
    }

    public User findByUsername(String username) {
        User u=usermapper.getUserByusername(username);
        return u;
    }

    public Set<String> findRoles(String username) {
        Set<String> roles=new HashSet<String>();
        User u=usermapper.getUserByusername(username);
        List<User_role> user_roles=u.getUser_roles();
        for(User_role ur:user_roles){
            Role r=ur.getRole();
            roles.add(r.getRole());
        }
        return roles;
    }

    public Set<String> findPermissions(String username) {
        Set<String> permissions=new HashSet<String>();
        User u=usermapper.getUserByusername(username);
        List<User_role> user_roles=u.getUser_roles();
        for(User_role ur:user_roles){
            Role role=rolemapper.getRolebyid(ur.getRole().getRoleid());
            List <Role_permission> rps=role.getRole_permissions();
            for(Role_permission rp:rps){
                String permission=rp.getPermission().getPermission();
                permissions.add(permission);
            }
        }
        return permissions;
    }

    public List<User> getallusers() {
        return usermapper.getusers();
    }

    public List<User> getpageusers(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum,pagesize);
        List<User> users=usermapper.getusers();
        return users;
    }

    public User updateuser(User u) {
        usermapper.updateuser(u);
        return u;
    }

    public void deleteuser(Long userid) {
        usermapper.deleteuser(userid);
    }

    public User getUser(Long uid) {
        User u=usermapper.getUserByid(uid);
        return u;
    }

    public void deleteuserroles(Long uid) {
        usermapper.deleteuseroles(uid);
    }


}
