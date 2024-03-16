package com.mogu.mic.data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: AutuSignMoguDing
 * @BelongsPackage: com.mogu.mic.entity
 * @Author: mi
 * @CreateTime: 2023/8/27 15:06
 * @Description:
 * @Version: 1.0
 */

@Data
@NoArgsConstructor
public class MogudingUser {

    /**
     * 对接数据库id
     */
    private Integer id;

    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    private String userId;
    private String token;
    private String userType;

    private String planId;
    private String planName;
    private String email;

    private String submitType;
    private String submitTitle;

    private String startTime;
    private String endTime;

    private Integer weekTotalFlag;
    private String content;

    private Integer isVipType;


    public MogudingUser(String username, String password, Integer id, String email) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.email = email;
    }

    public MogudingUser(String username, String password, Integer id, String email, Integer isVipType) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.email = email;
        this.isVipType = isVipType;
    }
}
