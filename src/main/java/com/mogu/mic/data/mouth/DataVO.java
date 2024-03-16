package com.mogu.mic.data.mouth;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: mi
 * @Data: 2023/11/26 15:56
 * @Description:
 */


@NoArgsConstructor
@Data
public class DataVO {
    @SerializedName("totalCount")
    private Integer totalCount;
    @SerializedName("pageSize")
    private Integer pageSize;
    @SerializedName("totalPage")
    private Integer totalPage;
    @SerializedName("currPage")
    private Integer currPage;
    @SerializedName("isDeleted")
    private Integer isDeleted;
    @SerializedName("createBy")
    private String createBy;
    @SerializedName("modifiedBy")
    private Object modifiedBy;
    @SerializedName("createTime")
    private String createTime;
    @SerializedName("modifiedTime")
    private String modifiedTime;
    @SerializedName("createByName")
    private Object createByName;
    @SerializedName("modifiedByName")
    private Object modifiedByName;
    @SerializedName("orderBy")
    private String orderBy;
    @SerializedName("sort")
    private String sort;
    @SerializedName("reportId")
    private String reportId;
    @SerializedName("studentId")
    private String studentId;
    @SerializedName("schoolId")
    private String schoolId;
    @SerializedName("planId")
    private String planId;
    @SerializedName("reportType")
    private String reportType;
    @SerializedName("title")
    private String title;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("attachments")
    private Object attachments;
    @SerializedName("videoUrl")
    private Object videoUrl;
    @SerializedName("state")
    private Integer state;
    @SerializedName("snowFlakeId")
    private Integer snowFlakeId;
    @SerializedName("weeks")
    private String weeks;
    @SerializedName("startTime")
    private String startTime;
    @SerializedName("endTime")
    private String endTime;
    @SerializedName("isFine")
    private Object isFine;
    @SerializedName("applyId")
    private Object applyId;
    @SerializedName("applyName")
    private Object applyName;
    @SerializedName("score")
    private Integer score;
    @SerializedName("scoreLevel")
    private Object scoreLevel;
    @SerializedName("address")
    private String address;
    @SerializedName("headImg")
    private String headImg;
    @SerializedName("supportNum")
    private Object supportNum;
    @SerializedName("commentNum")
    private Object commentNum;
    @SerializedName("isOnTime")
    private Integer isOnTime;
    @SerializedName("reportTabName")
    private Object reportTabName;
    @SerializedName("stuTabName")
    private Object stuTabName;
    @SerializedName("reDetalTabName")
    private Object reDetalTabName;
    @SerializedName("teaStuTabName")
    private Object teaStuTabName;
    @SerializedName("reportComments")
    private Object reportComments;
    @SerializedName("batchId")
    private Object batchId;
    @SerializedName("depId")
    private Object depId;
    @SerializedName("teaId")
    private Object teaId;
    @SerializedName("majorId")
    private Object majorId;
    @SerializedName("classId")
    private Object classId;
    @SerializedName("depIds")
    private Object depIds;
    @SerializedName("classIds")
    private Object classIds;
    @SerializedName("majorIds")
    private Object majorIds;
    @SerializedName("reportIds")
    private Object reportIds;
    @SerializedName("content")
    private String content;
    @SerializedName("commentContent")
    private Object commentContent;
    @SerializedName("reject")
    private Object reject;
    @SerializedName("username")
    private String username;
    @SerializedName("studentNumber")
    private Object studentNumber;
    @SerializedName("depName")
    private Object depName;
    @SerializedName("majorName")
    private Object majorName;
    @SerializedName("majorField")
    private Object majorField;
    @SerializedName("className")
    private Object className;
    @SerializedName("teacherName")
    private Object teacherName;
    @SerializedName("planName")
    private Object planName;
    @SerializedName("backup")
    private Object backup;
    @SerializedName("applyTime")
    private Object applyTime;
    @SerializedName("startTimeStr")
    private Object startTimeStr;
    @SerializedName("endTimeStr")
    private Object endTimeStr;
    @SerializedName("grade")
    private Object grade;
    @SerializedName("starNum")
    private Object starNum;
    @SerializedName("dateTime")
    private Object dateTime;
    @SerializedName("yearmonth")
    private String yearmonth;
    @SerializedName("attachmentList")
    private Object attachmentList;
    @SerializedName("userId")
    private String userId;
    @SerializedName("schoolName")
    private Object schoolName;
    @SerializedName("isSeeEnd")
    private Object isSeeEnd;
    @SerializedName("isCanRead")
    private Object isCanRead;
    @SerializedName("t")
    private Object t;
    @SerializedName("sign")
    private Object sign;
    @SerializedName("imageList")
    private Object imageList;
    @SerializedName("jobId")
    private String jobId;
    @SerializedName("companyName")
    private String companyName;
    @SerializedName("jobName")
    private String jobName;
    @SerializedName("reportTime")
    private Object reportTime;
    @SerializedName("isReject")
    private Object isReject;
}
