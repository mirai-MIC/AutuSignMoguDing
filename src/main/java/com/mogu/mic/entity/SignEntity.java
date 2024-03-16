package com.mogu.mic.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @BelongsProject: AutuSignMoguDing
 * @BelongsPackage: com.mogu.mic.entity
 * @Author: mi
 * @CreateTime: 2023/8/27 19:52
 * @Description: sign签到表
 * @Version: 1.0
 */


@Data
@TableName(value = "sign")
public class SignEntity {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 账号/电话号码
     */
    @TableField(value = "username", insertStrategy = FieldStrategy.NEVER)  //插入时，排除主键自增
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;


    /**
     * 国家
     */
    @TableField(value = "country")
    private String country;

    /**
     * 省
     */
    @TableField(value = "province")
    private String province;

    /**
     * 城市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 区县
     */
    @TableField(value = "area")
    private String area;

    /**
     * 维度
     */
    @TableField(value = "latitude")
    private String latitude;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private String longitude;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;


    @TableField(value = "type")
    private Integer type;

    //签到状态
    @TableField(value = "state")
    private Integer state;

}
