package com.mogu.mic.service;

import com.dtflys.forest.annotation.*;
import com.mogu.mic.data.LoginData;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @BelongsProject: AutuSignMoguDing
 * @BelongsPackage: com.mogu.mic.service
 * @Author: mi
 * @CreateTime: 2023/8/27 14:59
 * @Description:
 * @Version: 1.0
 */


@Component
@ComponentScan
@BaseRequest(
        baseURL = "${mogudingApi}",
        headers = {
                "User-Agent: ${userAgent}",
                "Content-Type: application/json; charset=UTF-8"
        },
        connectTimeout = 100000
)
public interface AutoSignClient {
    @Post("/session/user/v3/login")
    String login(@JSONBody LoginData data);

    @Post("/practice/plan/v3/getPlanByStu")
    @Headers({
            "roleKey: ${roleKey}",
            "sign: ${sign}",
            "authorization: ${token}"
    })
    String getPlanId(@Var("roleKey") String roleKey,
                     @Var("sign") String sign,
                     @Var("token") String token,
                     @JSONBody("state") String state);


    @Post("/attendence/clock/v2/save")
    @Headers({
            "roleKey: ${roleKey}",
            "sign: ${sign}",
            "authorization: ${token}"
    })
    void sign(@Var("roleKey") String roleKey,
              @Var("sign") String sign,
              @Var("token") String token,
              @JSONBody Map<String, String> data);

    @Post("/practice/paper/v2/listByStu")
    @Headers({
            "roleKey: ${roleKey}",
            "sign: ${sign}",
            "authorization: ${token}"
    })
    String get_week_count(@Var("roleKey") String roleKey,
                          @Var("sign") String sign,
                          @Var("token") String token,
                          @JSONBody Map<String, Object> data);

    @Post("/practice/paper/v1/getWeeks1")
    @Headers({
            "roleKey: ${roleKey}",
            "sign: ${sign}",
            "authorization: ${token}"
    })
    String getWeeks(@Var("roleKey") String roleKey,
                    @Var("sign") String sign,
                    @Var("token") String token,
                    @JSONBody Map<String, String> data);


    @Post("/practice/paper/v2/save")
    @Headers({
            "roleKey: ${roleKey}",
            "sign: ${sign}",
            "authorization: ${token}"
    })
    String submitWeek(@Var("roleKey") String roleKey,
                      @Var("sign") String sign,
                      @Var("token") String token,
                      @JSONBody Map<String, Object> data);


}
