package me.kyrene.springboot4integration.service.shiro;


import me.kyrene.springboot4integration.pojo.shiro.Permission;

import java.util.List;

/**
 * Created by wanglin on 2018/1/18.
 */
public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
    public void deletePermissions(Long permissionId);
    public List<Permission> getPermissions();
    public Permission getPermissionByid(Long permissionid);
    public Permission updatePermission(Permission permission);
    public List<Permission> getPagePermissions(int pagenum, int pagesize);
}
