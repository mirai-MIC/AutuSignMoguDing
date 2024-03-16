package com.mogu.mic.data.week.weektime;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: mi
 * @Data: 2023/11/25 23:13
 * @Description:
 */


@NoArgsConstructor
@Data
public class DataVO {
    @SerializedName("isDefault")
    private Integer isDefault;
    @SerializedName("weeks")
    private String weeks;
    @SerializedName("startTime")
    private String startTime;
    @SerializedName("endTime")
    private String endTime;
}
