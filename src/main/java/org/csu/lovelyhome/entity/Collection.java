package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 用户收藏表
 * </p>
 *
 * @author lqm
 * @since 2019-08-29
 */
@TableName("collection")
public class Collection implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 收藏id
     */
    @TableId(value = "collect_id", type = IdType.AUTO)
    private Integer collectId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 户型id
     */
    private Integer huxingId;

    /**
     * 收藏时间
     */
    private LocalDateTime time;


    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHuxingId() {
        return huxingId;
    }

    public void setHuxingId(Integer huxingId) {
        this.huxingId = huxingId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Collection{" +
        "collectId=" + collectId +
        ", userId=" + userId +
        ", huxingId=" + huxingId +
        ", time=" + time +
        "}";
    }
}
