package org.csu.lovelyhome.pojo.param;

public class PageParam {
    private Integer pageSize = 0;
    private Integer pageNum = 10;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
