package com.CMD.CMD_pro.survey.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//설문조사 보기 선택
public class SurveyResultVO {
    private int surveyResultIndex;
    private int surveyIndex;
    private int surveyItemIndex;
    private int userIndex;
    private Date surveyResultReg;

//    public SurveyResultVO() {}
//
//    public SurveyResultVO(SurveyResultVO resultCopy) {
//        this.surveyResultIndex = resultCopy.surveyResultIndex;
//        this.surveyItemIndex = resultCopy.surveyItemIndex;
//        this.userIndex = resultCopy.userIndex;
//        this.surveyResultReg = resultCopy.surveyResultReg;
//    }

    @Override
    public String toString() {
        return "SurveyResultVO [surveyResultIndex=" + surveyResultIndex + ", surveyItemIndex=" + surveyItemIndex
                + ", userIndex=" + userIndex + ", surveyResultReg=" + surveyResultReg + "]";
    }

}
