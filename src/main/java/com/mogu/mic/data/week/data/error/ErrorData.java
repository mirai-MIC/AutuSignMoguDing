package com.mogu.mic.data.week.data.error;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: mi
 * @CreateTime: 2023/11/3 12:12
 * @Description:
 */


@NoArgsConstructor
@Data
public class ErrorData {

    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String message;
    @SerializedName("request_id")
    private String requestId;
}
