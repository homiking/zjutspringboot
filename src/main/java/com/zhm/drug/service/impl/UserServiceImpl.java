package com.zhm.drug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhm.drug.entity.User;
import com.zhm.drug.mapper.UserMapper;
import com.zhm.drug.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public User queryUserByUsername(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        return userMapper.selectOne(wrapper);
    }

    @Override
    public int addUser(User user) {
       return userMapper.insert(user);
    }

    @Override
    public int findUserByName(String username) {

        User user = userMapper.findUserByName(username);
        if(user == null) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public User findUser(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public int editUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public void updatePass(Map<String, Object> map) {
        userMapper.updatePass(map);
    }
}
