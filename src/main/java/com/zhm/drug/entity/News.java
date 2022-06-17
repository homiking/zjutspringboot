package com.zhm.drug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("news")
@Data
public class News {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
}
