package com.zhm.drug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhm.drug.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据名字查找用户
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username};")
    public User findUserByName(String username);


    @Update("update user set password = #{newPass} where id = #{userId}")
    public void updatePass(Map<String, Object> map);
}
