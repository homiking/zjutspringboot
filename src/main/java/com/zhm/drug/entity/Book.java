package com.zhm.drug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @Author kknever
 * @Date 2022/5/8
 **/
@TableName("book")
@Data
public class Book {
    @TableField(value="id")
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;

    private String name;
    private BigDecimal price;
    private String author;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value="createtime")
    private Date createTime;
    private String cover;
    private String userId;
    // TableField注解表示数据库不存在的字段，而Java中需要使用，加上这个注解就不会报错
    @TableField(exist = false)
    private String username;
}
