package me.kyrene.springboot4integration.DAO.shiro;


import me.kyrene.springboot4integration.pojo.shiro.Role;

import java.util.List;

/**
 * Created by wanglin on 2018/1/18.
 */
public interface RoleMapper {
    List<Role> getRoles();
    void addRole(Role role);
    void deleterole(long roleid);//删除一个角色
    void addRolePermission(long roleid, long permissionid);
    void deleterole_permission(long roleid, long permissionid);//删除一个角色和一个权限的关联
    void deleteroles(long roleid);//删除一个角色的所有权限关联
    Role getRolebyid(long roleid);
    void updateRole(Role role);
}
