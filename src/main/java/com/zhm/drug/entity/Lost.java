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
 * @Description 失物招领
 * @Author kknever
 * @Date 2022/5/8
 **/
@TableName("lost")
@Data
public class Lost {
    @TableField(value="id")
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;

    private String name;
    private String lostinfo;
    private String picture;
    private String lostplace;
    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date losttime;

}
