package com.mogu.mic.service.impl;

import com.dtflys.forest.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mogu.mic.data.LoginData;
import com.mogu.mic.data.MogudingUser;
import com.mogu.mic.data.week.weekcount.WeekTotalJson;
import com.mogu.mic.data.week.weektime.DataVO;
import com.mogu.mic.data.week.weektime.WeekJson;
import com.mogu.mic.entity.SignEntity;
import com.mogu.mic.mapper.SignMapper;
import com.mogu.mic.service.AutoSignAPi;
import com.mogu.mic.service.AutoSignClient;
import com.mogu.mic.util.AutoSignUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static com.mogu.mic.util.AutoSignUtil.extractYearMonth;

/**
 * @BelongsProject: AutuSignMoguDing
 * @BelongsPackage: com.mogu.mic.service.Impl
 * @Author: mi
 * @CreateTime: 2023/8/27 15:09
 * @Description:
 * @Version: 1.0
 */


@Component
@Slf4j
public class AutoSignServiceImpl implements AutoSignAPi {
    @Resource
    private AutoSignClient client;

    @Resource
    private SignMapper signMapper;

    @Resource
    private MailServiceImpl mailService;


    /**
     * 构建周报日报所需数据
     *
     * @param user
     * @param encrypt
     * @param signById
     * @return
     */
    @NotNull
    private static HashMap<String, Object> getDatatHashMap(MogudingUser user, String encrypt, SignEntity signById, String YearMonth) {
        var mapweek = new HashMap<String, Object>();
        mapweek.put("yearmonth", user.getSubmitType().equals("month") ? extractYearMonth(YearMonth) : "");
        mapweek.put("address", "");
        mapweek.put("t", encrypt);
        mapweek.put("title", user.getSubmitTitle());
        mapweek.put("longitude", signById.getLongitude());
        mapweek.put("latitude", signById.getLatitude());
        mapweek.put("weeks", "第" + user.getWeekTotalFlag() + "周");
        mapweek.put("endTime", user.getEndTime());
        mapweek.put("startTime", user.getStartTime());
        mapweek.put("planId", user.getPlanId());
        mapweek.put("reportType", user.getSubmitType());
        mapweek.put("content", user.getContent());
        return mapweek;
    }

    /**
     * 登录
     *
     * @param user
     */
    @Override
    public void login(MogudingUser user) {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()))
            log.error("[参数异常] - 账号或密码不能为空");
        var loginData = new LoginData();
        loginData.setPhone(AutoSignUtil.encrypt(user.getUsername()));
        loginData.setPassword(AutoSignUtil.encrypt(user.getPassword()));
        loginData.setLoginType("android");
        loginData.setT(AutoSignUtil.encrypt(String.format("%d", System.currentTimeMillis())));

        System.out.println(loginData);
        var loginJson = client.login(loginData);
        JsonObject loginSuccess = AutoSignUtil.parseJson(loginJson, "登录", JsonObject.class);

        user.setUserId(loginSuccess.get("userId").getAsString());
        user.setUserType(loginSuccess.get("userType").getAsString());
        user.setToken(loginSuccess.get("token").getAsString());
        System.out.println();
        log.info("用户 {} 登录成功", user.getEmail());
    }

    /**
     * 获取PlainId
     *
     * @param user
     */
    @Override
    public void getPlanId(MogudingUser user) {
        String sign = AutoSignUtil.signMd5(user.getUserId() + user.getUserType());
        String planJson = client.getPlanId(user.getUserType(), sign, user.getToken(), "");
        var dataArray = AutoSignUtil.parseJson(planJson, "获取计划ID", JsonArray.class);
        if (!dataArray.isEmpty()) {
            JsonObject planObject = dataArray.get(0).getAsJsonObject();// Assuming you want the first element
            user.setPlanId(planObject.get("planId").getAsString());
            user.setPlanName(planObject.get("planName").getAsString());
        } else {
            log.error("No plan data found in the response.");
        }
    }

    /**
     * 签到
     *
     * @param user
     */
    @Override
    public void sign(MogudingUser user) {
        var body = getBody(user.getPlanId(), user);
        String sign = body.get("device") + body.get("type") + user.getPlanId() + user.getUserId() + body.get("address");
        sign = AutoSignUtil.signMd5(sign);
        client.sign(user.getUserType(), sign, user.getToken(), body);
        log.info("签到成功!!!");
        state(user);
        try {
            final String subject = "签到姬来报";
            mailService.sendHtmlMail(user.getEmail(), subject, "email/a.html");
        } catch (Exception e) {
            log.error("邮件发送失败", e);
        }

    }

    @Override
    public void getWeekCount(MogudingUser user) throws ParseException {
        HashMap<String, String> map0 = new HashMap<>();
        map0.put("planId", user.getPlanId());
        String weeks = client.getWeeks("", "", user.getToken(), map0);
        WeekJson weekJson = new Gson().fromJson(weeks, WeekJson.class);
        DataVO dataVO = weekJson.getData().get(0);
        String startTime = dataVO.getStartTime();
        String endTime = dataVO.getEndTime();
        log.info("开始时间: {}", startTime);
        log.info("结束时间: {}", endTime);

        user.setStartTime(startTime);
        user.setEndTime(endTime);
        String sign = AutoSignUtil.signMd5(user.getUserId() + "student%s".formatted(user.getSubmitType()));
        String encrypt = AutoSignUtil.encrypt(AutoSignUtil.timeShift(user.getStartTime()));
        HashMap<String, Object> map = new HashMap<>();
        map.put("reportType", user.getSubmitType());
        map.put("currPage", "1");
        map.put("pageSize", "25");
        map.put("planId", "");
        map.put("t", encrypt);

        String weekCount = client.get_week_count(user.getUserType(), sign, user.getToken(), map);

        WeekTotalJson weekTotalJson = new Gson().fromJson(weekCount, WeekTotalJson.class);
        int flag = weekTotalJson.getFlag();
        log.info("第{}周", flag + 1);
        user.setWeekTotalFlag(flag + 1);

    }

    /**
     * 提交周报或日报
     *
     * @param user
     */
    @Override
    public void submitWeeklyOrDaily(MogudingUser user) throws ParseException {

        String signWeek = AutoSignUtil.signMd5(user.getUserId() + user.getSubmitType() + user.getPlanId() + user.getSubmitTitle());
        String encrypt = AutoSignUtil.encrypt(AutoSignUtil.timeShift(user.getStartTime()));

        SignEntity signById = signMapper.selectById(user.getId());
        var mapweek = getDatatHashMap(user, encrypt, signById, user.getStartTime());

        String output = client.submitWeek(user.getUserType(), signWeek, user.getToken(), mapweek);
        String asString = new Gson().fromJson(output, JsonObject.class).get("msg").getAsString();
        log.info("提交{}结果: {}", user.getSubmitTitle(), asString);
        try {
            mailService.sendSimpleMail(user.getEmail(), "提交%s结果".formatted(user.getSubmitTitle()), asString);
        } catch (Exception e) {
            log.error("邮件发送失败: {}", e.getMessage());
        }
    }

    /**
     * 构建签到时所需数据
     *
     * @param planId 计划ID
     * @param user   用户
     * @return 签到时所需数据
     */
    private Map<String, String> getBody(String planId, MogudingUser user) {

        SignEntity signById = signMapper.selectById(user.getId());

        Map<String, String> params = new HashMap<>();
        String TypeStr = "START";
        LocalTime currentTime = LocalTime.now();
        if (currentTime.isAfter(LocalTime.NOON)) {
            TypeStr = "END";
        }

        params.put("country", signById.getCountry());
        params.put("address", signById.getAddress());
        params.put("province", signById.getProvince());
        params.put("city", signById.getCity());
        params.put("state", "1");
        params.put("area", signById.getArea());
        params.put("latitude", signById.getLatitude());
        params.put("longitude", signById.getLongitude());
        params.put("description", "今日打卡签到");
        params.put("planId", planId);
        params.put("type", TypeStr);
        params.put("device", "Android");
        return params;
    }

    //修改状态
    public void state(MogudingUser user) {
        SignEntity signEntity = new SignEntity();
        signEntity.setId(user.getId());
        signEntity.setState(1);
        signMapper.updateById(signEntity);
    }
}
