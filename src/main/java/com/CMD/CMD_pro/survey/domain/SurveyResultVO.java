package com.CMD.CMD_pro.survey.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//설문조사 참여정보
public class SurveyResultVO {
    private int survey_result_index;
    private int survey_index;
    private int survey_item_index;
    private int user_index; //참여자
    private Date survey_result_reg; //참여일

    public SurveyResultVO() {}

    public SurveyResultVO(SurveyResultVO resultCopy) {
        this.survey_result_index = resultCopy.survey_result_index;
        this.survey_item_index = resultCopy.survey_item_index;
        this.user_index = resultCopy.user_index;
        this.survey_result_reg = resultCopy.survey_result_reg;
    }

    @Override
    public String toString() {
        return "SurveyResultVO [survey_result_index=" + survey_result_index + ", survey_item_index=" + survey_item_index
                + ", user_index=" + user_index + ", survey_result_reg=" + survey_result_reg + "]";
    }
}
