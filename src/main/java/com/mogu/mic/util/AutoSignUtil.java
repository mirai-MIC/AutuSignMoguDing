package com.mogu.mic.util;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.AES;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mogu.mic.enums.ResponseConstant;
import com.mogu.mic.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @BelongsProject: AutuSignMoguDing
 * @BelongsPackage: com.mogu.mic.util
 * @Author: mi
 * @CreateTime: 2023/8/27 15:41
 * @Description:
 * @Version: 1.0
 */

@Slf4j
public class AutoSignUtil {
    private static final byte[] secret = "23DbtQHR2UMbH6mJ".getBytes(StandardCharsets.UTF_8);
    private static final AES aes = new AES("ECB", "PKCS7Padding", secret);

    private static final String SIGN_SUFFIX = "3478cbbc33f84bd00d75d7dfa69e0daa";

    private static final MD5 md5 = MD5.create();
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    private static final String REGEX = "%\\w+%";

    public static String encrypt(String str) {
        return HexUtil.encodeHexStr(aes.encrypt(str));
    }

    public static String signMd5(String str) {
        str += SIGN_SUFFIX;
        return md5.digestHex(str, StandardCharsets.UTF_8);
    }

    public static <T> T parseJson(String jsonString, String oprName, Class<T> t) {
        var jsonElement = JsonParser.parseString(jsonString);

        if (jsonElement == null || jsonElement.isJsonNull()) {
            throw new BusinessException(oprName + "失败，无效的JSON格式！");
        }

        if (!jsonElement.isJsonObject()) {
            throw new BusinessException(oprName + "失败，JSON数据不是一个对象！");
        }

        var jsonObject = jsonElement.getAsJsonObject();

        if (!jsonObject.has("code") || !jsonObject.has("msg") || !jsonObject.has("data")) {
            throw new BusinessException(oprName + "失败，无效的JSON格式！");
        }

        int code = jsonObject.get("code").getAsInt();
        if (code != ResponseConstant.SUCCESS.getValue()) {
            throw new BusinessException(oprName + "失败，返回原因：" + jsonObject.get("msg").getAsString());
        }

        JsonElement dataElement = jsonObject.get("data");
        if (dataElement == null || dataElement.isJsonNull()) {
            throw new BusinessException(oprName + "失败，获取结果失败！");
        }

        return new Gson().fromJson(dataElement, t);
    }

    /**
     * 读取本地html文件里的html代码
     *
     * @param file File file=new File("文件的绝对路径")
     */
    public static String toHtmlString(File file) {
        // 获取HTML文件流
        StringBuilder htmlSb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), StandardCharsets.UTF_8));
            while (br.ready()) {
                htmlSb.append(br.readLine());
            }
            br.close();
        } catch (IOException e) {
            log.error("读取本地html文件里的html代码失败", e);
        }
        return htmlSb.toString();
    }

    /**
     * 将时间字符串转换为时间戳
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String timeShift(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate = sdf.parse(date);
        long time = parsedDate.getTime();
        return String.valueOf(Long.parseLong(time + "000") - 3600);
    }

    public static String extractYearMonth(String timeString) {
        LocalDateTime dateTime = LocalDateTime.parse(timeString, INPUT_FORMATTER);
        return dateTime.format(OUTPUT_FORMATTER);
    }


    /**
     * 替换字符串中的占位符
     *
     * @param text
     * @param replacement
     * @return
     */
    public static String replace(String text, String replacement) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
