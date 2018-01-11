package me.kyrene.springboot4integration.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.kyrene.springboot4integration.pojo.User;
import me.kyrene.springboot4integration.service.IUserService;
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
    private IUserService userService;

    @ApiOperation(value = "获取所有用户", notes = "")//方法描述
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();

    }

    @ApiOperation(value = "查找用户", notes = "根据UserID查找用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")//参数描述
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserByID(@PathVariable Long id) {
        return userService.getUserByID(id);
    }

    @ApiOperation(value = "更新用户", notes = "根据UserID更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long"),//参数描述
            @ApiImplicitParam(name = "user", value = "用户对象", required = true, dataType = "User")//参数描述
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public int updateUserByID(@PathVariable Long id, @ModelAttribute User user) {
        User user1 = userService.getUserByID(id);
        if (user != null) {
            user1.setAge(user.getAge());
            user1.setName(user.getName());
        }
        return userService.updateUserByID(user1);
    }

    @ApiOperation(value = "删除用户", notes = "根据UserID删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")//参数描述
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int deleteUserByID(@PathVariable Long id) {
        return userService.deleteUserByID(id);
    }

    @ApiOperation(value = "新建用户", notes = "需要传入除ID以外的参数,id自增长")
    @ApiImplicitParam(name = "user", value = "user对象", required = true, dataType = "User")//参数描述
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public int insertUser(@ModelAttribute User user) {
        return userService.insertUser(user);
    }
}
