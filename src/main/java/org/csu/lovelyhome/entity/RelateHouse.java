package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 出租房评论及回复关系表
 * </p>
 *
 * @author lqm
 * @since 2019-09-03
 */
@TableName("relate_house")
public class RelateHouse implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 关系id
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
        return "RelateHouse{" +
        "relateId=" + relateId +
        ", commentId=" + commentId +
        ", responseId=" + responseId +
        "}";
    }
}
