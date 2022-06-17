package com.zhm.drug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhm.drug.entity.Teacher;
import com.zhm.drug.entity.User;
import com.zhm.drug.mapper.UserMapper;
import com.zhm.drug.service.IUserService;
import com.zhm.drug.service.cacheconstant.CacheConstant;
import com.zhm.drug.util.RedisUtil;
import com.zhm.drug.util.TokenUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Map;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@CacheConfig(cacheNames = CacheConstant.USER_CACHE_ID)
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtil redisUtil;
    @Override
    public User queryUserByUsername(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        return userMapper.selectOne(wrapper);
    }

    @Override
    public int addUser(User user) {
        String password = user.getPassword();
        user.setPassword(new SimpleHash("md5", password, null, 1024).toString());
        return userMapper.insert(user);
    }

    @Override
    public int findUserByName(String username) {
        User user = userMapper.findUserByName(username);
        if (user == null) {
            return 0;
        } else {
            return 1;
        }
    }
    @Override
    public User findUser(String username) {
        String key = CacheConstant.USER_CACHE_KEY_PREFIX + username;
        User user = redisUtil.getCacheObject(key);
        if(user == null) {
            synchronized (this.getClass()) {
                User user1 = redisUtil.getCacheObject(key);
                if(user1 == null) {
                    System.out.println("查询了数据库");
                    User userByName = userMapper.findUserByName(username);
                    redisUtil.setCacheObject(key, userByName);
                    return userByName;
                } else {
                    System.out.println("查询了同步代码块");
                    return user1;
                }
            }
        } else {
            System.out.println("缓存");
        }
        return user;
    }

    @Override
    public int editUser(User user) {

        return userMapper.updateById(user);
    }

    //   代码实现缓存
//    @Override
//    public Object selectById(Integer id) {
//        String key = "user:" + id;
//        Object userobj = redisTemplate.opsForValue().get(key);
//        User res = null;
//        if(userobj == null) {
//            synchronized (this.getClass()) {
//                userobj = redisTemplate.opsForValue().get(key);
//                if(userobj == null) {
//                    System.out.println("从数据库查");
//                    res = userMapper.selectById(id);
//                    if(res == null) {
//                        return null;
//                    }
//                    redisTemplate.opsForValue().set(key, res);
//                } else {
//                    System.out.println("从同步代码块缓存查");
//                    return redisTemplate.opsForValue().get(key);
//                }
//            }
//
//        } else {
//            System.out.println("从缓存查");
//        }
//        return (User)userobj;
//    }
    // 注解实现缓存
    @Override
    public Object selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public void updatePass(Map<String, Object> map) {
        userMapper.updatePass(map);
    }

    @Override
    public IPage<User> selectUserPage(int pageNum, int pageSize, String param) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(! StringUtils.isEmpty(param)) {
            // like 是模糊查询, eq 是完全相等的查询
            wrapper.like("username", param);
        }
        Page<User> page = new Page<>(pageNum, pageSize);
        return userMapper.selectPage(page, wrapper);
    }
}
