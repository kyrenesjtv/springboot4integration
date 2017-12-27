package me.kyrene.springboot4integration.DAO;

import me.kyrene.springboot4integration.pojo.User;

import java.util.List;

/**
 * Created by wanglin on 2017/12/27.
 */
public interface UserDAO {
    /**
     * 返回所有的users
     *
     * @return
     */
    List<User> getAllUsers();

    /**
     * 通过id寻找User
     *
     * @param id
     * @return
     */
    User getUserByID(Long id);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    int updateUserByID(User user);

    /**
     * 通过id删除用户
     *
     * @param id
     * @return
     */
    int deleteUserByID(Long id);

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);
}
