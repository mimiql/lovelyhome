package org.csu.lovelyhome.entity;

import java.util.List;

/**
 * 个人中心的所有的评论
 * @author zjx by 2019/9/2
 */
public class Comment {

    private List<CommentBuilding> commentBuildings;
    private List<CommentDecorate> commentDecorates;
    private List<CommentHouse> commentHouses;

    public List<CommentBuilding> getCommentBuildings() {
        return commentBuildings;
    }

    public List<CommentDecorate> getCommentDecorates() {
        return commentDecorates;
    }

    public void setCommentBuildings(List<CommentBuilding> commentBuildings) {
        this.commentBuildings = commentBuildings;
    }

    public void setCommentDecorates(List<CommentDecorate> commentDecorates) {
        this.commentDecorates = commentDecorates;
    }

    public List<CommentHouse> getCommentHouses() {
        return commentHouses;
    }

    public void setCommentHouses(List<CommentHouse> commentHouses) {
        this.commentHouses = commentHouses;
    }
}
