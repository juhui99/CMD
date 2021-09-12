package com.CMD.CMD_pro.survey.service;

import com.CMD.CMD_pro.survey.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurveyService {
    public List<SurveyVO> getSurveyList(SearchCriteria cri) throws Exception;  // 리스트 + 검색 + 페이징
//    int countInfoListTotal(SearchCriteria scri) throws Exception;
    public PageMaker getPagination(SearchCriteria cri) throws Exception;
//    List<SurveyVO> getSurveyItemList(int surveyIndex) throws Exception;
//    int selectApplyCnt(SurveyVO userSurvey) throws Exception;
    public SurveyVO getSurvey(int survey_index) throws Exception;
    public SurveyWithItemVO getSurveyItems(int survey_index) throws Exception;
    public SurveyWithDatasetVO getSurveyResult(int survey_index) throws Exception;
    public void addSurvey(SurveyVO surveyVO, SurveyWithItemVO surveyWithItemVO);
    public void	closeSurvey(int survey_index);
    public void removeSurvey(int survey_index);
    public void insertSurveyResult(SurveyResultVO srvo);
//    public List<SurveyVO> getSearchMember(SearchCriteria cri);
//    public void removeSurveyUnabled(String[] surseqlist, String realPath);
}
