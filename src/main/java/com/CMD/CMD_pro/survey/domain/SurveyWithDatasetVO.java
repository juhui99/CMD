package com.CMD.CMD_pro.survey.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SurveyWithDatasetVO extends SurveyWithItemVO {
    private List<ResultDataSet> dataset;

    public SurveyWithDatasetVO(SurveyWithItemVO surveyWithItemVO) {
        super(surveyWithItemVO);
    }
}
