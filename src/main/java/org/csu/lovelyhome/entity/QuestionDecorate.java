package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 装修方案提问及回复表
 * </p>
 *
 * @author lqm
 * @since 2019-09-02
 */
@TableName("question_decorate")
public class QuestionDecorate implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 装修方案提问id
     */
    private Integer questionId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 时间
     */
    private LocalDateTime time;

    /**
     * 提问内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Integer likeNum;

    /**
     * 类型0回复 1提问
     */
    private Integer type;

    /**
     * 装修方案id
     */
    private Integer decorateId;


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

    public Integer getDecorateId() {
        return decorateId;
    }

    public void setDecorateId(Integer decorateId) {
        this.decorateId = decorateId;
    }

    @Override
    public String toString() {
        return "QuestionDecorate{" +
        "questionId=" + questionId +
        ", userId=" + userId +
        ", time=" + time +
        ", content=" + content +
        ", likeNum=" + likeNum +
        ", type=" + type +
        ", decorateId=" + decorateId +
        "}";
    }
}
