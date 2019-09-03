package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 装修方案提问回复关系表
 * </p>
 *
 * @author lqm
 * @since 2019-09-02
 */
@TableName("relate_question_decorate")
public class RelateQuestionDecorate implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 关系id
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

    @Override
    public String toString() {
        return "RelateQuestionDecorate{" +
        "relateId=" + relateId +
        ", questionId=" + questionId +
        ", responseId=" + responseId +
        "}";
    }
}
