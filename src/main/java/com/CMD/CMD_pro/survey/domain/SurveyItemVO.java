package com.CMD.CMD_pro.survey.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
// 설문 항목 저장
public class SurveyItemVO {
    private int survey_item_index;
    private int survey_index;
    private String  survey_item_content; //내용

    @Override
    public String toString() {
        return "SurveyItemVO [survey_item_index=" + survey_item_index + ", survey_index=" + survey_index + ", survey_item_content="
                + survey_item_content + "]";
    }

    public static List<SurveyItemVO> initSurveyItemVO(ResultSet rs) throws SQLException {
        List<SurveyItemVO> itemList = new ArrayList<SurveyItemVO>();
        while (rs.next()) {
            SurveyItemVO surveyItemVO = new SurveyItemVO();
            surveyItemVO.setSurvey_item_index(rs.getInt("survey_item_index"));
            surveyItemVO.setSurvey_index(rs.getInt("survey_index"));
            surveyItemVO.setSurvey_item_content(rs.getString("survey_item_content"));
            itemList.add(surveyItemVO);
        }
        return itemList;
    }

}
