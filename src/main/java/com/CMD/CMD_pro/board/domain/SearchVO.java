package com.CMD.CMD_pro.board.domain;

import java.util.Date;

public class SearchVO {
    private int search_index;
    public String keyword;
    public int count;

    public int getSearch_index() {
        return search_index;
    }

    public void setSearch_index(int search_index) {
        this.search_index = search_index;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
