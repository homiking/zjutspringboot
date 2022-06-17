package com.zhm.drug.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhm.drug.entity.UserRole;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
public interface IUserRoleService extends IService<UserRole> {
    int insert(UserRole userRole);
}
