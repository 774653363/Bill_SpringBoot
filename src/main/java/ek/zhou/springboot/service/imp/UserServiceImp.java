package ek.zhou.springboot.service.imp;

import ek.zhou.springboot.entities.Provider;
import ek.zhou.springboot.entities.User;
import ek.zhou.springboot.mapper.ProviderMapper;
import ek.zhou.springboot.mapper.UserMapper;
import ek.zhou.springboot.service.ProviderService;
import ek.zhou.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务层实现类
 *
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImp implements UserService {
    //注入mapper
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUsers(User user) {
        return userMapper.getUsers(user);
    }

    @Cacheable(key = "#id")
    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User addUser(User user) {
        userMapper.addUser(user);
        return user;
    }

    @Override
    @CacheEvict(key = "#id")
    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    @CachePut(key = "#user.id")
    public User updateUser(User user) {
        userMapper.updateUser(user);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
