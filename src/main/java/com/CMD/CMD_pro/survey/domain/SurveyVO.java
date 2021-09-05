package com.CMD.CMD_pro.survey.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Getter
@Setter
public class SurveyVO {
    private int surveyIndex;
    private int userIndex;
    private String surveyTitle; //제목
    private String surveyContent; //내용
    private Date surveyReg; //시작시간
    private Date surveyEnd; //종료시간
    private int progressing; //진행여부 1:진행중인, 0:마감된 설문
    private int surveyCount;
    private int surveyDuplicate;

    public SurveyVO(SurveyVO surveyCopy) {
        this.surveyIndex = surveyCopy.surveyIndex;
        this.userIndex = surveyCopy.userIndex;
        this.surveyTitle = surveyCopy.surveyTitle;
        this.surveyContent = surveyCopy.surveyContent;
        this.surveyReg = surveyCopy.surveyReg;
        this.surveyEnd = surveyCopy.surveyEnd;
        this.progressing = surveyCopy.progressing;
        this.surveyCount = surveyCopy.surveyCount;
        this.surveyDuplicate = surveyCopy.surveyDuplicate;
    }

    public SurveyVO() {
    }

    public void initSurveyVO(ResultSet rs) throws SQLException {
        this.setSurveyIndex(rs.getInt("surveyIndex"));
        this.setSurveyTitle(rs.getString("surveyTitle"));
        this.setUserIndex(rs.getInt("userIndex"));
        this.setSurveyContent(rs.getString("surveyContent"));
        this.setSurveyReg(rs.getDate("surveyReg"));
        this.setSurveyEnd(rs.getDate("surveyEnd"));
        this.setProgressing(rs.getInt("progressing"));
    }

}
