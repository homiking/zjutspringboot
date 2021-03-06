package com.zhm.drug.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhm.drug.entity.User;

import java.util.Map;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
public interface IUserService extends IService<User> {
    public User queryUserByUsername(User user);

    /**
     * 新增管理员
     */
    public int addUser(User user);

    /**
     * 根据用户名查询 user
     * @param username
     * @return
     */
    public int findUserByName(String username);

    /**
     * 查找一条用户，保存登录信息在浏览器
     * @param username
     * @return
     */
    public User findUser(String username);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public int editUser(User user);

    public Object selectById(Integer id);

    void  updatePass(Map<String, Object> map);

    /**
     * 查找用户列表
     * @return
     */
    IPage<User> selectUserPage(int pageNum, int pageSize, String param);
}
