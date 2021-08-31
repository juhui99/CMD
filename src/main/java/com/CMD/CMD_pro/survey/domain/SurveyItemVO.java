package com.CMD.CMD_pro.survey.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
// 진행중인 설문
public class SurveyItemVO {
    private int surveyItemIndex;
    private int surveyIndex;
    private String  surveyItemContent;

    public static List<SurveyItemVO> initSurveyItemVO(ResultSet rs) throws SQLException {
        List<SurveyItemVO> itemList = new ArrayList<SurveyItemVO>();
        while (rs.next()) {
            SurveyItemVO surveyItemVO = new SurveyItemVO();
            surveyItemVO.setSurveyItemIndex(rs.getInt("surveyItemIndex"));
            surveyItemVO.setSurveyIndex(rs.getInt("surveyIndex"));
            surveyItemVO.setSurveyItemContent(rs.getString("surveyItemContent"));
            itemList.add(surveyItemVO);
        }
        return itemList;
    }

}
