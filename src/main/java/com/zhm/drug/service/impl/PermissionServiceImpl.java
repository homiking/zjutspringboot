package com.zhm.drug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhm.drug.entity.Permission;
import com.zhm.drug.entity.RolePermission;
import com.zhm.drug.mapper.PermissionMapper;
import com.zhm.drug.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<RolePermission> getRolePermissionByRoleId(Integer roleId) {
        return permissionMapper.getRolePermissionByRoleId(roleId);
    }

    @Override
    public Permission selectById(Integer permissionId) {
        return permissionMapper.selectById(permissionId);
    }
    @Override
    public IPage<Permission> selectPermissionPage(int pageNum, int pageSize, String param) {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        if(! StringUtils.isEmpty(param)) {
            // like 是模糊查询, eq 是完全相等的查询
            wrapper.like("name", param);
        }
        Page<Permission> page = new Page<>(pageNum, pageSize);
        return permissionMapper.selectPage(page, wrapper);
    }

    @Override
    public int addPermission(Permission permission) {
        return permissionMapper.insert(permission);
    }

    @Override
    public int editPermission(Permission permission) {
        return permissionMapper.updateById(permission);
    }

    @Override
    public Permission queryPermissionById(int id) {
        return permissionMapper.selectById(id);
    }

    @Override
    public int delPermissionById(int id) {
        return permissionMapper.deleteById(id);
    }

    @Override
    public List<Permission> queryAllPermission() {
        return permissionMapper.selectList(null);
    }

    @Override
    public List<Permission> selectList() {
        return permissionMapper.selectList(null);
    }

    @Override
    public int deletePermissionsByRoleId(Integer roleId) {
        return permissionMapper.deletePermissionsByRoleId(roleId);
    }

    @Override
    public int insertRoleAndPermission(Integer roleId, Integer permissionId) {
        return permissionMapper.insertRoleAndPermission(roleId, permissionId);
    }
}
