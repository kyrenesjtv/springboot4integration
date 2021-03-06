package me.kyrene.springboot4integration.service.impl;

import me.kyrene.springboot4integration.DAO.UserDAO;
import me.kyrene.springboot4integration.aspect.annotation.RedisCacheService;
import me.kyrene.springboot4integration.pojo.User;
import me.kyrene.springboot4integration.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wanglin on 2017/12/27.
 */
@Service
public class UserServiceIMPL implements IUserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }



    @Override
    @RedisCacheService(group = {"userDetail"},cacheOperation= RedisCacheService.CACHE_OPERATION.GET,use = true)
    public User getUserByID(Long id) {
        return userDAO.getUserByID(id);
    }

    @Override
    @Transactional(value = "TMManager1")
    @RedisCacheService(group = {"userDetail"},cacheOperation= RedisCacheService.CACHE_OPERATION.UPDATE,use = true)
    public int updateUserByID(User user) {
        return userDAO.updateUserByID(user);
    }

    @Override
    //@Transactional(value = "TMManager1")
    @RedisCacheService(group = {"userDetail"},cacheOperation= RedisCacheService.CACHE_OPERATION.DELETE,use = true)
    public int deleteUserByID(Long id) {
        return userDAO.deleteUserByID(id);
    }

    @Override
    //@Transactional(value = "TMManager1")
    @RedisCacheService(group = {"userDetail"},cacheOperation= RedisCacheService.CACHE_OPERATION.INSERT,use = true)
    public int insertUser(User user) {
        return userDAO.insertUser(user);
    }
}
