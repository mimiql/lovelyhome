package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 提问回复关系表
 * </p>
 *
 * @author lqm
 * @since 2019-08-29
 */
@TableName("relate_question")
public class RelateQuestion implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 提问与回复关系id
     */
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
        return "RelateQuestion{" +
        "relateId=" + relateId +
        ", questionId=" + questionId +
        ", responseId=" + responseId +
        "}";
    }
}
