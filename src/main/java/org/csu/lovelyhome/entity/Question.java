package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 楼盘提问或回复表
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@TableName("question")
public class Question implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 提问id
     */
    @TableId(value = "question_id", type = IdType.AUTO)
    private Integer questionId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 提问或回复时间
     */
    private LocalDateTime time;

    /**
     * 内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Integer likeNum;

    /**
     * 类型 0回复 1提问
     */
    private Integer type;

    /**
     * 楼盘id
     */
    private Integer buildingId;


    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    @Override
    public String toString() {
        return "Question{" +
        "questionId=" + questionId +
        ", userId=" + userId +
        ", time=" + time +
        ", content=" + content +
        ", likeNum=" + likeNum +
        ", type=" + type +
        ", buildingId=" + buildingId +
        "}";
    }
}
