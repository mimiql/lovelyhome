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
 * @since 2019-08-31
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
     * 被收藏对象id
     */
    private Integer objectId;

    /**
     * 收藏时间
     */
    private LocalDateTime time;

    /**
     * 收藏类型：1楼盘 2户型 3出租房 4装修方案
     */
    private Integer type;


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

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Collection{" +
        "collectId=" + collectId +
        ", userId=" + userId +
        ", objectId=" + objectId +
        ", time=" + time +
        ", type=" + type +
        "}";
    }
}
