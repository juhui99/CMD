package com.CMD.CMD_pro.survey.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
public class SearchCriteria extends Criteria{
    private String searchType; // 검색 타입(카테고리)
    private String progressing;
    private String search;
    private String keyword; // 검색 키워드

    @Override
    public String toString() {
        return "SearchCriteria [searchType=" + searchType + ",keyword=" + keyword + "]";
    }

    public String makeSearch() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("perPageNum", this.getPerPageNum())
                .queryParam("searchType", this.getSearchType())
                .queryParam("progressing", this.getProgressing())
                .queryParam("search", this.getSearch())
                .queryParam("keyword", this.getKeyword())
                .build();
        return uriComponents.toUriString();
    }

    public String getProgressing() {
        if (progressing == null || progressing.isEmpty()) {
            return "1";
        }
        return progressing;
    }

}
