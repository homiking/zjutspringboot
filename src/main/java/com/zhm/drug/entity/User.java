package com.zhm.drug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@Data
@TableName(value = "user")
public class User implements Serializable {
    //主键
    @TableField(value="id")
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String Avatar;
    private String nickname;
    private Integer age;
    private String gender;
    private String address;

    @TableField(exist = false)
    private List<Integer> roles;

    @TableField(exist = false)
    private String token;

    @TableField(exist = false)
    private Set<Permission> permissions;
}
