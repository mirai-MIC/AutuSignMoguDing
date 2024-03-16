package com.mogu.mic.data;

import lombok.Data;

/**
 * @BelongsProject: AutuSignMoguDing
 * @BelongsPackage: com.mogu.mic.entity
 * @Author: mi
 * @CreateTime: 2023/8/27 15:01
 * @Description:
 * @Version: 1.0
 */


@Data
public class LoginData {
    private String phone;
    private String password;
    private String t;
    private String loginType;
    private String uuid;
}
