package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 浏览记录表
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@TableName("browse")
public class Browse implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 浏览id
     */
    @TableId(value = "browse_id", type = IdType.AUTO)
    private Integer browseId;

    /**
     * 用户id
     */
    private Integer useId;

    /**
     * 被浏览对象id
     */
    private Integer objectId;

    /**
     * 浏览时间
     */
    private LocalDateTime time;

    /**
     * 浏览类型 1楼盘 2户型 3出租房 4装修方案
     */
    private Integer type;


    public Integer getBrowseId() {
        return browseId;
    }

    public void setBrowseId(Integer browseId) {
        this.browseId = browseId;
    }

    public Integer getUseId() {
        return useId;
    }

    public void setUseId(Integer useId) {
        this.useId = useId;
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
        return "Browse{" +
        "browseId=" + browseId +
        ", useId=" + useId +
        ", objectId=" + objectId +
        ", time=" + time +
        ", type=" + type +
        "}";
    }
}