package com.CMD.CMD_pro.survey.domain;

public class Criteria {
    private int page; //현재 페이지 번호
    private int perPageNum; //페이지당 출력할 게시글 수

    // sql문에서 pageStart에 들어갈 값.
    // 게시글 몇번째 부터 시작할 지 결정
    public int getPageStart() {
        return (this.page - 1) * this.perPageNum;
    }

    //초기값 세팅
    public Criteria() {
        this.page = 1; //현재 페이지
        this.perPageNum = 5;//페이지당 출력할 데이터 수
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page <= 0) {
            this.page = 1;
        } else{
            this.page = page;
        }
    }

    public int getPerPageNum() {
        return perPageNum;
    }

//    public void setPerPageNum(int perPageNum) {
//        if (this.perPageNum <= 0 || this.perPageNum > 50) {
//            this.perPageNum = 10;
//            return;
//        }
//        this.perPageNum = perPageNum;
//    }
    public void setPerPageNum(int pageCount) {
        int cnt = this.perPageNum;
        if(pageCount != cnt) {
            this.perPageNum = cnt;
        } else {
            this.perPageNum = pageCount;
        }
    }


}
