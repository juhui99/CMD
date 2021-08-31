package com.CMD.CMD_pro.survey.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultDataSet {
    private int user_age;
    private int user_gender;
    private String user_name;
    private int surveyItemIndex;

    @Override
    public String toString() {
        return "ResultDataSet [age=" + user_age + ", gender=" + user_gender + ", name=" + user_name + ", survey_item_seq="
                + surveyItemIndex + "]";
    }
}