package com.zhm.drug.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhm.drug.common.Result;
import com.zhm.drug.entity.Permission;
import com.zhm.drug.entity.RolePermission;
import com.zhm.drug.entity.User;
import com.zhm.drug.entity.UserRole;
import com.zhm.drug.enums.RoleEnum;
import com.zhm.drug.service.IPermissionService;
import com.zhm.drug.service.IRoleService;
import com.zhm.drug.service.IUserRoleService;
import com.zhm.drug.service.IUserService;
import com.zhm.drug.util.TokenUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@CacheConfig(cacheNames = "userrole")
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    IUserService userService;

    @Autowired
    IPermissionService permissionService;

    @Autowired
    IRoleService roleService;

    @Autowired
    IUserRoleService userRoleService;
    /**
     * 登录验证方法
     * @param user 请求传进来用户对象
     * @param
     * @return
     */


    @PostMapping("/login")
    @ResponseBody
    public Result<?> login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        password = new SimpleHash("md5",password,null,1024).toString();
        if(username == null || password == null) {
            return Result.error("203","用户名和密码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken(username, password);
        try{
            subject.login(authenticationToken);

        } catch (UnknownAccountException e) {
            return Result.error("204","用户名不存在");
        } catch (IncorrectCredentialsException  e) {
            return Result.error("203","密码不正确");
        }

        User res = userService.findUser(username);
        res.setPassword(null);
        HashSet<Permission> permissionSet = new HashSet<>();
        // 1. 从user_role表通过用户id查询所有的角色信息
        Integer userId = res.getId();
        List<UserRole> userRoles = roleService.getUserRoleByUserId(userId);
        // 设置角色id
        res.setRoles(userRoles.stream().map(UserRole::getRoleId).distinct().collect(Collectors.toList()));
        for (UserRole userRole : userRoles) {
            // 2.根据roleId从role_permission表查询出所有的permissionId
            List<RolePermission> rolePermissions = permissionService.getRolePermissionByRoleId(userRole.getRoleId());
            for (RolePermission rolePermission : rolePermissions) {
                Integer permissionId = rolePermission.getPermissionId();
                // 3. 根据permissionId查询permission信息
                Permission permission = permissionService.selectById(permissionId);
                permissionSet.add(permission);
            }
        }
        // 对资源根据id进行排序
        LinkedHashSet<Permission> sortedSet = permissionSet.stream().sorted(Comparator.comparing(Permission::getId)).collect(Collectors.toCollection(LinkedHashSet::new));
        //设置当前用户的资源信息
        res.setPermissions(sortedSet);

        // 生成token
        String token =  TokenUtils.genToken(res);
        res.setToken(token);
        return Result.success(res);
    }
    @PostMapping("/register")
    public Object toRegister(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken(username, password);
        try{
            int i = userService.findUserByName(username);
            if(i == 1) {
                return Result.error("206", "用户名太受欢迎了");
            }
            User registerUser = new User();
            registerUser.setUsername(username);
            registerUser.setPassword(password);
            userService.addUser(registerUser);
            //User res = userService.findUser(username);
            UserRole userRole = UserRole.builder()
                    .userId(registerUser.getId())
                    .roleId(RoleEnum.USER.getRoleId())
                    .build();
            userRoleService.insert(userRole);

            //User res = userService.findUser(username);
            return Result.success();
        } catch (UnknownAccountException e) {
            return Result.error("207", "用户名不存在");
        } catch (IncorrectCredentialsException  e) {
            return Result.error("206", "密码不正确");
        }
    }

    @PutMapping("/editUser")
    public Result<?> update(@RequestBody User user){
        try {
            userService.editUser(user);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Integer id) {
        Object user = userService.selectById(id);
        if(user == null) {
            return Result.error("400", "该用户不存在");
        } else {
            return Result.success(user);
        }
    }
    @GetMapping("/redis/{id}")
    public Object redisGetById(@PathVariable("id") Integer id) {
        ExecutorService es = Executors.newFixedThreadPool(200);
        for (int i = 0; i < 50; i++) {
            es.submit(() -> userService.selectById(id));
        }
        return userService.selectById(id);
    }

    @PutMapping("/pass")
    public Result<?> pass(@RequestBody Map<String, Object> map) {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        User user = (User)userService.selectById((Integer) map.get("userId"));
        if (user== null) {
            return Result.error("-1", "未找到用户");
        }
//        if (!bc.matches(map.get("password").toString(), user.getPassword())) {
//            return Result.error("-1", "密码错误");
//        }
        if(! map.get("password").toString().equals(user.getPassword())) {
            return Result.error("620","密码不正确，请重新输入");
        }
        //map.put("newPass", (bc.encode(map.get("newPass").toString())));
        userService.updatePass(map);
        return Result.success();
    }

    @Cacheable(key="'userpage'")
    @GetMapping("/queryUserRole")
    public Result<?> queryUserRole(String search, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize ){
        try{
            IPage<User> ipage = userService.selectUserPage(pageNum, pageSize, search);
            List<User> records = ipage.getRecords();
            // 给用户设置绑定的角色id数组
            for (User record : records) {
                Integer userId = record.getId();
                List<Integer> roles = roleService.getUserRoleByUserId(userId).stream().map(UserRole::getRoleId).collect(Collectors.toList());
                record.setRoles(roles);
            }
            return Result.success(ipage);
        } catch (Exception e) {
            return Result.error("404", "查询不到数据");
        }

    }
    @CacheEvict(key="'userpage'")
    @PutMapping("/changeRole")
    public Result<?> changePermission(@RequestBody User user, HttpServletRequest request) {
        // 先根据用户id删除所有的用户和角色的绑定关系
        roleService.deleteRoleByUserId(user.getId());
        // 再新增 新的绑定关系
        for (Integer roleId : user.getRoles()) {
            roleService.insertUserAndRole(user.getId(), roleId);
        }
        return Result.success(false);
    }
}
