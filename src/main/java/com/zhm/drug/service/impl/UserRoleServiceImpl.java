package com.zhm.drug.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhm.drug.entity.UserRole;
import com.zhm.drug.mapper.UserRoleMapper;
import com.zhm.drug.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public int insert(UserRole userRole) {
        return userRoleMapper.insert(userRole);
    }
}
