package ek.zhou.springboot.mapper;

import ek.zhou.springboot.entities.User;

import java.util.List;

/**
 * 用户mapper接口
 */
public interface UserMapper {

    /**
     * 条件查询用户列表
     * @param user
     * @return
     */
    public List<User> getUsers(User user);

    /**
     * 根据pid查询用户
     * @param id
     * @return
     */
    public User getUserById(Integer id);

    /**
     * 插入用户
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 根据pid删除用户
     * @param id
     * @return
     */
    public int deleteUserById(Integer id);

    /**
     * 更新用户
     * @param user
     * @return
     */
    public int updateUser(User user);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User getUserByUsername(String username);
}
