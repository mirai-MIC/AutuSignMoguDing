package com.mogu.mic.data.week.weekcount;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: mi
 * @Data: 2023/11/25 23:21
 * @Description:
 */


@NoArgsConstructor
@Data
public class WeekTotalJson {

    @SerializedName("code")
    private Integer code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private List<DataVO> data;
    @SerializedName("flag")
    private Integer flag;
}
