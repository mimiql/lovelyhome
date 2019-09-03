package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 聊天记录表
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@TableName("message")
public class Message implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "message_id", type = IdType.AUTO)
    private Integer messageId;

    private Integer fromId;

    private Integer toId;

    private String content;

    private Integer type;

    private Date time;

    private Integer isTransport;


    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getIsTransport() {
        return isTransport;
    }

    public void setIsTransport(Integer isTransport) {
        this.isTransport = isTransport;
    }

    @Override
    public String toString() {
        return "Message{" +
        "messageId=" + messageId +
        ", fromId=" + fromId +
        ", toId=" + toId +
        ", content=" + content +
        ", type=" + type +
        ", time=" + time +
        ", isTransport=" + isTransport +
        "}";
    }
}
