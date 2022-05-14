package com.zhm.drug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@Data
@TableName(value = "supplier")
public class Supplier implements Serializable {
    //主键
    @TableField(value="id")
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createtime;
}