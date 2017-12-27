package me.kyrene.springboot4integration.service.impl;

import me.kyrene.springboot4integration.DAO.UserDAO;
import me.kyrene.springboot4integration.pojo.User;
import me.kyrene.springboot4integration.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User getUserByID(Long id) {
        return userDAO.getUserByID(id);
    }

    @Override
    public int updateUserByID(User user) {
        return userDAO.updateUserByID(user);
    }

    @Override
    public int deleteUserByID(Long id) {
        return userDAO.deleteUserByID(id);
    }

    @Override
    public int insertUser(User user) {
        return userDAO.insertUser(user);
    }
}