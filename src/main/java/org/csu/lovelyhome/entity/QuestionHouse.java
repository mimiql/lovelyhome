package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 出租房提问及回复记录表
 * </p>
 *
 * @author lqm
 * @since 2019-09-03
 */
@TableName("question_house")
public class QuestionHouse implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 出租房提问或回复id
     */
    @TableId(value = "question_id", type = IdType.AUTO)
    private Integer questionId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 时间
     */
    private Date time;

    /**
     * 提问内容
     */
    private String content;

    /**
     * 图片
     */
    private String picture;

    /**
     * 点赞数
     */
    private Integer likeNum;

    /**
     * 类型：1提问 0回复
     */
    private Integer type;

    /**
     * 出租房id
     */
    private Integer houseId;

    /**
     * 状态：0正在审核 1已发布
     */
    private Integer status;


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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "QuestionHouse{" +
        "questionId=" + questionId +
        ", userId=" + userId +
        ", time=" + time +
        ", content=" + content +
        ", picture=" + picture +
        ", likeNum=" + likeNum +
        ", type=" + type +
        ", houseId=" + houseId +
        ", status=" + status +
        "}";
    }
}
