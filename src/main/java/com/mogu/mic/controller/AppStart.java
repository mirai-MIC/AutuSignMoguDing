package com.mogu.mic.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mogu.mic.data.MogudingUser;
import com.mogu.mic.mapper.SignMapper;
import com.mogu.mic.mapper.WeekMapper;
import com.mogu.mic.service.AiServiceApi;
import com.mogu.mic.service.impl.AutoSignServiceImpl;
import com.mogu.mic.service.impl.MailServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.mogu.mic.util.AutoSignUtil.replace;

/**
 * @BelongsProject: AutuSignMoguDing
 * @BelongsPackage: com.mogu.mic.controller
 * @Author: mi
 * @CreateTime: 2023/8/27 15:55
 * @Description:
 * @Version: 1.0
 */

@Component
@Slf4j
public class AppStart {

    @Resource
    private AutoSignServiceImpl mogudingService;

    @Resource
    private AiServiceApi aiServiceApi;
    @Resource
    private SignMapper signMapper;

    @Resource
    private WeekMapper weekMapper;

    @Resource
    private MailServiceImpl mailService;

    @Async
    @Scheduled(cron = "0 0 6 * * ?") // 每天6点执行一次
    public void OK() {
        log.info("确认存活");
    }

    //每天中文12点15执行一次
    @Async
    @Scheduled(cron = "0 0 12 * * ?")
    public void up() {
        update();
    }


    @Async
    @Scheduled(cron = "0 5 12 * * ?")
    public void up1() {
        update();
    }

    //每天晚上12执行一次
    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void up2() {
        update();
    }
    @Async
    @Scheduled(cron = "0 2 0 * * ?")
    public void up3() {
        update();
    }
    /**
     * 早上签到
     */

//    @PostConstruct
    @Async
    @Scheduled(cron = "0 0 8 * * ?")
    public void morningSign() {
        performSign();
    }

    //每天早上8点05执行一次
    @Async
    @Scheduled(cron = "0 5 8 * * ?")
    public void morningSign2() {
        performSign();
    }

    /**
     * 晚上签到
     */
    @Async
    @Scheduled(cron = "0 0 18 * * ?")
    public void eveningSign() {
        performSign();
    }

    //每天晚上18点05执行一次
    @Async
//    @PostConstruct
    @Scheduled(cron = "0 5 18 * * ?")
    public void eveningSign2() {
        performSign();
    }


//    @Async
//    //每月第一天早上8点执行一次
//    @Scheduled(cron = "0 0 8 1 * ?")
//    public void start() {
//        weekSubmit("month", "月报");
//    }


    //每周周五下午5点执行一次
    @Async
//    @PostConstruct
    @Scheduled(cron = "0 0 17 ? * FRI")
    public void WeeksEnd() {
        weekSubmit("week", "周报");
    }

    @Async
//    @PostConstruct
    @Scheduled(cron = "0 2 17 ? * FRI")
    public void WeeksEnd2() {
        weekSubmit("week", "周报");
    }


    //每天下午5点执行一次
//    @Async
//    @Scheduled(cron = "0 0 17 * * ?")
//    public void WeeksStart() {
//        weekSubmit("day", "日报");
//    }


    @SneakyThrows(Exception.class)
    public void performSign() {
        signMapper.getAllUsers()
                .stream()
                .map(user -> new MogudingUser(user.getUsername(), user.getPassword(), user.getId(), user.getEmail()))
                .forEach(mogudingUser -> {
                    try {
                        if (signMapper.getAllState(mogudingUser.getId()).getState() == 0) return;
                        mogudingService.login(mogudingUser);
                        mogudingService.getPlanId(mogudingUser);
                        mogudingService.sign(mogudingUser);
                    } catch (Exception e) {
                        log.error("签到失败", e);
                        mailService.sendSimpleMail(mogudingUser.getEmail(), "签到失败,等待5分钟后二次续签，如果再次出现签到失败，请联系管理员，周知，概不负责", e.getMessage());
                    }
                });
    }


    @SneakyThrows(Exception.class)
    public void weekSubmit(String WeekOrMouth, String Title) {
        signMapper.getAllUsers().stream()
                .map(user -> new MogudingUser(user.getUsername(), user.getPassword(), user.getId(), user.getEmail(), user.getType()))
                .forEach(mogudingUser -> {
                    try {
                        if (mogudingUser.getIsVipType() != 0) return;

                        mogudingService.login(mogudingUser);
                        mogudingService.getPlanId(mogudingUser);
                        mogudingUser.setSubmitTitle(Title);
                        mogudingUser.setSubmitType(WeekOrMouth);
                        mogudingService.getWeekCount(mogudingUser);
                        mogudingUser.setContent(WeekContent(mogudingUser.getStartTime(), String.valueOf(mogudingUser.getWeekTotalFlag()), Title));
                        mogudingService.submitWeeklyOrDaily(mogudingUser);
                    } catch (Exception e) {
                        log.error("报告提交失败", e);
                    }
                });
    }

    /**
     * 构造报告方法
     *
     * @param time  时间
     * @param weeks xx周
     * @param Title 周报/月报/日报
     */
    public String WeekContent(String time, String weeks, String Title) {
        int randomIndex = (int) (Math.random() * weekMapper.selectCount(Wrappers.emptyWrapper()));
        val article = weekMapper.selectById(randomIndex).getArticle();
        return replace(article, weeks);

    }


    public void update() {
        signMapper.selectList(null).forEach(signEntity -> {
            signEntity.setState(0);
            signMapper.updateById(signEntity);
        });
    }
}

