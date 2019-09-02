package org.csu.lovelyhome.entity;

import java.util.List;

/**
 * 楼盘页面详情
 * @author zjx by 2019/9/1
 */
public class BuildingDetail {
    private Building building;
    private List<CommentBuilding> commentBuildings;
    private List<Question> questionBuildings;
    private List<Question> responses;
    private List<Huxing> huxings;
    private List<Integer> allNumsOfQuestion;

    public Building getBuilding() {
        return building;
    }

    public List<CommentBuilding> getCommentBuildings() {
        return commentBuildings;
    }

    public List<Question> getQuestionBuildings() {
        return questionBuildings;
    }

    public List<Question> getResponses() {
        return responses;
    }

    public List<Huxing> getHuxings() {
        return huxings;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setCommentBuildings(List<CommentBuilding> commentBuildings) {
        this.commentBuildings = commentBuildings;
    }

    public void setQuestionBuildings(List<Question> questionBuildings) {
        this.questionBuildings = questionBuildings;
    }

    public void setResponses(List<Question> responses) {
        this.responses = responses;
    }

    public void setHuxings(List<Huxing> huxings) {
        this.huxings = huxings;
    }

    public List<Integer> getAllNumsOfQuestion() {
        return allNumsOfQuestion;
    }

    public void setAllNumsOfQuestion(List<Integer> allNumsOfQuestion) {
        this.allNumsOfQuestion = allNumsOfQuestion;
    }
}
