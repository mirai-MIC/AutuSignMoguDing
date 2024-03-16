package com.mogu.mic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: mi
 * @Data: 2024/1/7 15:59
 * @Description: 周报表
 */

@Data
@TableName("Weekly")
public class WeeklyEntity {
    /**
     * Id for the weekly
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    /**
     * article for the weekly
     */
    @TableField(value = "article")
    private String article;
}
