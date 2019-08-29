package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 装修评论与回复关系表
 * </p>
 *
 * @author lqm
 * @since 2019-08-29
 */
@TableName("relate_decorate")
public class RelateDecorate implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 装修评论与回复id
     */
    @TableId(value = "relate_id", type = IdType.AUTO)
    private Integer relateId;

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 回复id
     */
    private Integer responseId;


    public Integer getRelateId() {
        return relateId;
    }

    public void setRelateId(Integer relateId) {
        this.relateId = relateId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
    }

    @Override
    public String toString() {
        return "RelateDecorate{" +
        "relateId=" + relateId +
        ", commentId=" + commentId +
        ", responseId=" + responseId +
        "}";
    }
}
