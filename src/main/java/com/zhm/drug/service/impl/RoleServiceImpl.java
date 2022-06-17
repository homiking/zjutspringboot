package com.zhm.drug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhm.drug.entity.Role;
import com.zhm.drug.entity.UserRole;
import com.zhm.drug.mapper.RoleMapper;
import com.zhm.drug.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService{
    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<UserRole> getUserRoleByUserId(Integer userId) {
       return  roleMapper.getUserRoleByUserId(userId);
    }
    @Override
    public IPage<Role> selectRolePage(int pageNum, int pageSize, String param) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if(! StringUtils.isEmpty(param)) {
            // like 是模糊查询, eq 是完全相等的查询
            wrapper.like("name", param);
        }
        Page<Role> page = new Page<>(pageNum, pageSize);
        return roleMapper.selectPage(page, wrapper);
    }

    @Override
    public int addRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int editRole(Role role) {
        return roleMapper.updateById(role);
    }

    @Override
    public Role queryRoleById(int id) {
        return roleMapper.selectById(id);
    }

    @Override
    public int delRoleById(int id) {
        return roleMapper.deleteById(id);
    }

    @Override
    public List<Role> selectList() {
        return roleMapper.selectList(null);
    }

    @Override
    public int deleteRoleByUserId(Integer userId) {
        return roleMapper.deleteRolesByUserId(userId);
    }

    @Override
    public int insertUserAndRole(Integer userId, Integer roleId) {
        return roleMapper.insertUserAndRole(userId, roleId);
    }
}
