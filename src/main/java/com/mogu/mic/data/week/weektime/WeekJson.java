package com.mogu.mic.data.week.weektime;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: mi
 * @Data: 2023/11/25 23:12
 * @Description:
 */


@NoArgsConstructor
@Data
public class WeekJson {

    @SerializedName("code")
    private Integer code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private List<DataVO> data;
    @SerializedName("flag")
    private Integer flag;
}
