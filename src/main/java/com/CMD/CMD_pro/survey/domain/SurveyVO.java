package com.CMD.CMD_pro.survey.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Getter
@Setter
public class SurveyVO {
    private int survey_index;
    private int user_index;
    private String survey_title; //제목
    private String survey_content; //내용
    private Date survey_reg; //시작시간
    private Date survey_end; //종료시간
    private int progressing; //진행여부 1:진행중인, 0:마감된 설문
    private int survey_count;

    public SurveyVO(SurveyVO surveyCopy) {
        this.survey_index = surveyCopy.survey_index;
        this.user_index = surveyCopy.user_index;
        this.survey_title = surveyCopy.survey_title;
        this.survey_content = surveyCopy.survey_content;
        this.survey_reg = surveyCopy.survey_reg;
        this.survey_end = surveyCopy.survey_end;
        this.progressing = surveyCopy.progressing;
        this.survey_count = surveyCopy.survey_count;
    }

    public SurveyVO() {
    }

    public void initSurveyVO(ResultSet rs) throws SQLException {
        this.setSurvey_index(rs.getInt("survey_index"));
        this.setSurvey_title(rs.getString("survey_title"));
        this.setUser_index(rs.getInt("user_index"));
        this.setSurvey_count(rs.getInt("survey_content"));
        this.setSurvey_reg(rs.getDate("survey_reg"));
        this.setSurvey_end(rs.getDate("survey_end"));
        this.setProgressing(rs.getInt("progressing"));
    }
}
