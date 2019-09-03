package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 出租房评论及回复记录表
 * </p>
 *
 * @author lqm
 * @since 2019-09-03
 */
@TableName("comment_house")
public class CommentHouse implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 评论id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 时间
     */
    private Date time;

    /**
     * 文本
     */
    private String content;

    /**
     * 评论图片
     */
    private String picture;

    /**
     * 点赞数
     */
    private Integer likeNum;

    /**
     * 类型：1评论 2回复
     */
    private Integer type;

    /**
     * 出租房id
     */
    private Integer houseId;

    /**
     * 状态：0正在审核，1已发布
     */
    private Integer status;


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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
        return "CommentHouse{" +
        "commentId=" + commentId +
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
