package com.CMD.CMD_pro.survey.domain;

import lombok.Getter;
import lombok.Setter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Getter
@Setter
public class PageMaker {
    private Criteria cri; // Criteria를 주입받는다
    private int totalCount; //게시판 전체 게시글 개수
    private int startPage; // 현재 화면에서 보이는 startPage 번호
    private int endPage; // 현재 화면에 보이는 endPage 번호
    private boolean prev; // 페이징 이전 버튼 활성화 여부
    private boolean next; // 페이징 다음 버튼 활성화 여부
    private int totalPage; //총 출력되는 페이지수
    private int displayPageNum = 3; //총 출력할 페이지수 // 1,2,3,4,5

    @Override
    public String toString() {
        return "PageMaker [cri=" + cri + ", totalCount=" + totalCount + ", startPage=" + startPage
                + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum="
                + displayPageNum + "]";
    }

    public Criteria getCri() {
        return cri;
    }
    public void setCriteria(Criteria cri) {
        this.cri = cri;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {
        //마지막 번호
        endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
        //시작 번호
        startPage = (endPage - displayPageNum) + 1;
        if(startPage <= 0) startPage = 1;

        //마지막번호 재계산
        int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
        prev = startPage == 1 ? false : true;
        next = endPage * cri.getPerPageNum() < totalCount ? true : false;
    }

    // 페이지 쿼리 만드는 메소드
    public String makeQuery(int page) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", cri.getPerPageNum())
                .build();
        return uri.toUriString();
    }
    public String makeQuery(int idx, int page) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .queryParam("idx", idx)
                .queryParam("page", page)
                .queryParam("perPageNum", cri.getPerPageNum())
                .build();
        return uri.toUriString();
    }

    private String encoding(String keyword) {
        if(keyword == null || keyword.trim().length() == 0) {
            return "";
        }
        try {
            return URLEncoder.encode(keyword, "UTF-8");
        } catch(UnsupportedEncodingException e) {
            return "";
        }
    }

    // 리스트 + 검색 + 페이징
    public String makeSearch(SearchCriteria cri) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("perPageNum", cri.getPerPageNum())
                .queryParam("searchType", cri.getSearchType())
                .queryParam("keyword", cri.getKeyword())
                .build();
        return uriComponents.toUriString();
    }

}
