package com.mogu.mic.service;

import com.mogu.mic.data.MogudingUser;

import java.io.IOException;
import java.text.ParseException;

public interface AutoSignAPi {
    /**
     * 登录接口
     *
     * @param user
     */
    void login(MogudingUser user);

    /**
     * 获取planId
     *
     * @param user
     */
    void getPlanId(MogudingUser user);

    /**
     * 签到
     *
     * @param user
     */
    void sign(MogudingUser user) throws IOException;

    /**
     * 获取周报次数
     *
     * @param user
     * @throws IOException
     * @throws ParseException
     */
    void getWeekCount(MogudingUser user) throws IOException, ParseException;

    /**
     * 提交周报或日报
     *
     * @param user
     * @throws IOException
     */
    void submitWeeklyOrDaily(MogudingUser user) throws IOException, ParseException;
}
