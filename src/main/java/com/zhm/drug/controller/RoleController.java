package com.zhm.drug.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhm.drug.common.Result;
import com.zhm.drug.entity.Role;
import com.zhm.drug.entity.RolePermission;
import com.zhm.drug.entity.User;
import com.zhm.drug.entity.UserRole;
import com.zhm.drug.service.IPermissionService;
import com.zhm.drug.service.IRoleService;
import com.zhm.drug.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@CacheConfig(cacheNames = "rolepermission")
@RestController
@RequestMapping(value="/role")
public class RoleController {
    @Autowired
    IRoleService roleService;


    @Autowired
    IPermissionService permissionService;

    @CacheEvict(key="'rolepage'")
    @PostMapping("/add")
    public Result<?> addRole(@RequestBody Role role){

        try {
            roleService.addRole(role);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","添加失败");
        }
    }
    @CacheEvict(key="'rolepage'")
    @PutMapping("/editRole")
    public Result<?> update(@RequestBody Role role){

        try {
            roleService.editRole(role);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @CacheEvict(key="'rolepage'")
    @DeleteMapping("/deleteRole/{id}")
    public Result<?> delete(@PathVariable Integer id){
        try {
            roleService.delRoleById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @Cacheable(key="'rolepage'")
    @RequestMapping(value="/roleQueryPage")
    @ResponseBody
    public Result<?> roleQueryPage(String search, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize ){
        try{
            IPage<Role> ipage = roleService.selectRolePage(pageNum, pageSize, search);
            List<Role> records = ipage.getRecords();
            // 给角色设置绑定的权限id数组
            for (Role record : records) {
                Integer roleId = record.getId();
                List<Integer> permissions = permissionService.getRolePermissionByRoleId(roleId).stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
                record.setPermissions(permissions);
            }
            return Result.success(ipage);
        } catch (Exception e) {
            return Result.error("404", "查询不到数据");
        }

    }
    @CacheEvict(key="'rolepage'")
    @PutMapping("/changePermission")
    public Result<?> changePermission(@RequestBody Role role, HttpServletRequest request) {
        // 先根据角色id删除所有的角色跟权限的绑定关系
        permissionService.deletePermissionsByRoleId(role.getId());
        // 再新增 新的绑定关系
        for (Integer permissionId : role.getPermissions()) {
            permissionService.insertRoleAndPermission(role.getId(), permissionId);
        }
        System.out.println(request);
        // 判断当前登录的用户角色是否包含了当前操作行的角色id，如果包含，则返回true，需要重新登录。
        User user = TokenUtils.getUser();
        List<UserRole> userRoles = roleService.getUserRoleByUserId(user.getId());
        if (userRoles.stream().anyMatch(userRole -> userRole.getRoleId().equals(role.getId()))) {
            return Result.success(true);
        }
//        如果不包含，则返回false，不需要重新登录。
        return Result.success(false);
    }
    @GetMapping("/all")
    public Result<?> all() {
        return Result.success(roleService.selectList());
    }
}
