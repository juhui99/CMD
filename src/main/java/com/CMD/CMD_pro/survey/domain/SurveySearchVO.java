package com.CMD.CMD_pro.survey.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveySearchVO {
    private int search_index;
    public String keyword;
    public int count;
}