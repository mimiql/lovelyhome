package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 提问回复关系表
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@TableName("relate_question")
public class RelateQuestion implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 提问与回复关系id
     */
    @TableId(value = "relate_id", type = IdType.AUTO)
    private Integer relateId;

    /**
     * 提问id
     */
    private Integer questionId;

    /**
     * 回复id
     */
    private Integer responseId;

    /**
     * 类型 1 楼盘，2 装修方案
     */
    private Integer type;

    public Integer getRelateId() {
        return relateId;
    }

    public void setRelateId(Integer relateId) {
        this.relateId = relateId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RelateQuestion{" +
        "relateId=" + relateId +
        ", questionId=" + questionId +
        ", responseId=" + responseId +
        ", type=" + type +
        "}";
    }
}
