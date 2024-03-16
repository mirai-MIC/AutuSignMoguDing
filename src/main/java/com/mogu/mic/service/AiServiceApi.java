package com.mogu.mic.service;

import com.dtflys.forest.annotation.Headers;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.mogu.mic.data.week.data.Data;
import com.mogu.mic.data.week.data.output.OutPutData;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author: mi
 * @Data: 2023/11/26 14:20
 * @Description:
 */

@Component
@ComponentScan
public interface AiServiceApi {
    @Headers({
            "Authorization: Bearer ${apiKey}",
            "Content-Type: application/json",
    })
    @Post("https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation")
    OutPutData Ai(@JSONBody Data text);
}
