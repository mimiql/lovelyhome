package org.csu.lovelyhome.pojo.vo;

import org.csu.lovelyhome.entity.QuestionDecorate;

public class QuestionDecorateVO {
    private QuestionDecorate questionDecorate;
    private QuestionDecorate answerDecorate;

    public QuestionDecorate getQuestionDecorate() {
        return questionDecorate;
    }

    public void setQuestionDecorate(QuestionDecorate questionDecorate) {
        this.questionDecorate = questionDecorate;
    }

    public QuestionDecorate getAnswerDecorate() {
        return answerDecorate;
    }

    public void setAnswerDecorate(QuestionDecorate answerDecorate) {
        this.answerDecorate = answerDecorate;
    }
}
