package com.zhm.drug.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhm.drug.entity.Role;
import com.zhm.drug.entity.UserRole;

import java.util.List;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
public interface IRoleService extends IService<Role> {
    /**
     *  根据用户 id 查血角色
     * @param id
     * @return
     */
    List<UserRole> getUserRoleByUserId(Integer id);
    /**
     * 分页查询权限菜单数据
     * @param pageNum 页数
     * @param pageSize 每页多少条数据
     * @param param 查询参数
     * @return
     */
    public IPage<Role> selectRolePage(int pageNum, int pageSize, String param);

    /**
     * 添加权限菜单信息
     * @param role
     */
    public int addRole(Role role);

    /**
     * 修改权限菜单信息
     * @param role
     * @return
     */
    public int editRole(Role role);

    /**
     * 根据主键查询权限菜单信息
     * @param id
     * @return
     */
    public Role queryRoleById(int id);

    /**
     * 根据主键删除权限菜单信息
     * @param id
     * @return
     */
    public int delRoleById(int id);

    /**
     * 查询全部角色
     * @return
     */
    List<Role> selectList();

    int deleteRoleByUserId(Integer userId);

    int insertUserAndRole(Integer userId, Integer roleId);
}
