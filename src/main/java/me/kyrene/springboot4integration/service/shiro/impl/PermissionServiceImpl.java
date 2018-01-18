package me.kyrene.springboot4integration.service.shiro.impl;

import com.github.pagehelper.PageHelper;
import me.kyrene.springboot4integration.DAO.shiro.PermissionMapper;
import me.kyrene.springboot4integration.pojo.shiro.Permission;
import me.kyrene.springboot4integration.service.shiro.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wanglin on 2018/1/18.
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionmapper;

    public Permission createPermission(Permission permission) {
        permissionmapper.addpermission(permission);
        return permission;
    }

    public void deletePermission(Long permissionId) {
        permissionmapper.deletepermission(permissionId);
    }

    public List<Permission> getPermissions() {
        return permissionmapper.getPermissions();
    }

    public Permission getPermissionByid(Long permissionid) {
        return permissionmapper.getPermissionByid(permissionid);
    }

    public Permission updatePermission(Permission permission) {
        permissionmapper.updatePermission(permission);
        return permission;
    }

    public List<Permission> getPagePermissions(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum,pagesize);
        List<Permission> list=permissionmapper.getPermissions();
        return list;
    }

    public void deletePermissions(Long permissionId) {
        permissionmapper.deletePermissionsByid(permissionId);
    }


}
