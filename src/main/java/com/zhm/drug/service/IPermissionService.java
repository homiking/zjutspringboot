package com.zhm.drug.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhm.drug.entity.*;

import java.util.List;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
public interface IPermissionService extends IService<Permission> {
    /**
     * 根据用户 id 查询用户角色，可能有多个角色，返回列表
     * @param roleId
     * @return
     */
    List<RolePermission> getRolePermissionByRoleId(Integer roleId);

    /**
     * 根据 根据权限 id 返回权限。
     * @param permissionId
     * @return
     */
    Permission selectById(Integer permissionId);

    /**
     * 分页查询权限菜单数据
     * @param pageNum 页数
     * @param pageSize 每页多少条数据
     * @param param 查询参数
     * @return
     */
    public IPage<Permission> selectPermissionPage(int pageNum, int pageSize, String param);

    /**
     * 添加权限菜单信息
     * @param permission
     */
    public int addPermission(Permission permission);

    /**
     * 修改权限菜单信息
     * @param permission
     * @return
     */
    public int editPermission(Permission permission);

    /**
     * 根据主键查询权限菜单信息
     * @param id
     * @return
     */
    public Permission queryPermissionById(int id);

    /**
     * 根据主键删除权限菜单信息
     * @param id
     * @return
     */
    public int delPermissionById(int id);

    /**
     * 查询所有权限菜单数据
     * @return
     */
    public List<Permission> queryAllPermission();

    /**
     * 查询角色资源
     * @return
     */
    List<Permission> selectList();

    /**
     *  删除角色所有的权限
     * @param roleId
     * @return
     */
    int deletePermissionsByRoleId(Integer roleId);

    /**
     * 给角色增加权限
     * @param roleId
     * @param permissionId
     * @return
     */
    int insertRoleAndPermission(Integer roleId, Integer permissionId);
}
