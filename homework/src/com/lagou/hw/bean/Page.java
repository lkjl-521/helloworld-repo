package com.lagou.hw.bean;

/**
 * @author lkjl_java
 * @Description: 分页对象
 * @date 2021/2/21 - 17:13
 */
public class Page {
    private int nowPage; // 描述当前页码
    private int totalPage; // 描述总页码

    public Page() {
    }

    public Page(int nowPage, int totalPage) {
        this.nowPage = nowPage;
        this.totalPage = totalPage;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
