package me.kyrene.springboot4integration.controller;

import me.kyrene.springboot4integration.pojo.User;
import me.kyrene.springboot4integration.service.impl.UserServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wanglin on 2017/12/27.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserServiceIMPL userServiceIMPL;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userServiceIMPL.getAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserByID(@PathVariable Long id) {
        return userServiceIMPL.getUserByID(id) ;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public int updateUserByID(@PathVariable Long id, @ModelAttribute User user) {
        User user1 = userServiceIMPL.getUserByID(id);
        if (user != null) {
            user1.setAge(user.getAge());
            user1.setName(user.getName());
        }
        return userServiceIMPL.updateUserByID(user1);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int deleteUserByID(@PathVariable Long id) {
        return userServiceIMPL.deleteUserByID(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public int insertUser(@ModelAttribute User user) {
        return userServiceIMPL.insertUser(user);
    }
}
