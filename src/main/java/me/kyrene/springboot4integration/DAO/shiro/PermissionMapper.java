package me.kyrene.springboot4integration.DAO.shiro;


import me.kyrene.springboot4integration.pojo.shiro.Permission;

import java.util.List;

/**
 * Created by wanglin on 2018/1/18.
 */
public interface PermissionMapper {
    List<Permission> getPermissions();
    void addpermission(Permission permission);
    void deletepermission(long pid);
    Permission getPermissionByid(Long pid);
    Permission updatePermission(Permission permission);
    void deletePermissionsByid(Long permissionid);//删除所有角色中的permission
}
