package com.CMD.CMD_pro.survey.service.serviceimpl;

import com.CMD.CMD_pro.survey.domain.*;
import com.CMD.CMD_pro.survey.mapper.SurveyMapper;
import com.CMD.CMD_pro.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceimpl implements SurveyService {

    @Autowired
    private SurveyMapper surveyMapper;

    @Override
    public SurveyVO getSurvey(int survey_index) throws Exception {
        return surveyMapper.selectSurvey(survey_index);
    }

    @Override
    public List<SurveyVO> getSurveyList(SearchCriteria cri) throws Exception {
        return surveyMapper.selectSurveyList(cri);
    }

//    @Override
//    public PageMaker getPagination(SearchCriteria cri) throws Exception {
//        return surveyMapper.selectCountPaging(cri);
//    }

    @Override
    public SurveyWithItemVO getSurveyItems(int survey_index) throws Exception {
        SurveyVO surveyVO = getSurvey(survey_index);
        SurveyWithItemVO surveyWithItemVO = new SurveyWithItemVO(surveyVO);
        surveyWithItemVO.setSurveyItemList(surveyMapper.selectSurveyItems(survey_index));
//		surveyWithItemVO.setMySurvey(dao.selecyMySurveyResult(survey_seq, member_seq));
        return surveyWithItemVO;
    }
    @Override
    public void addSurvey(SurveyVO surveyVO, SurveyWithItemVO surveyWithItemVO) {
        surveyMapper.insertSurvey(surveyVO);
        surveyMapper.insertSurveyItem(surveyWithItemVO.getSurveyItemList());
    }

    @Override
    public SurveyWithDatasetVO getSurveyResult(int survey_index) throws Exception {
        SurveyWithItemVO surveyWithItemVO = getSurveyItems(survey_index);
        SurveyWithDatasetVO surveyWithDatasetVO = new SurveyWithDatasetVO(surveyWithItemVO);
        List<ResultDataSet> dataSetList = surveyMapper.selectSurveyResultDataSet(survey_index);
        surveyWithDatasetVO.setDataset(dataSetList);
        return surveyWithDatasetVO;
    }

    // 설문조사 보기 선택
    @Override
    public void insertSurveyResult(SurveyResultVO srvo) {
        surveyMapper.insertSurveyResult(srvo);
    }

//    @Override
//    public List<SurveyVO> getSearchMember(SearchCriteria cri) {
//        List<SurveyVO> list = surveyMapper.selectSearchSurvey(cri);
//        return list;
//    }
    @Override
    public void closeSurvey(int survey_index) {
        surveyMapper.closeSurvey(survey_index);
    }

    @Override
    public void removeSurvey(int survey_index) {
        surveyMapper.removeSurvey(survey_index);
    }


//    @Override
//    public List<SurveyVO> getSurveyItemList(int surveyIndex) throws Exception{
//        logger.info("selectSurveyItemList");
//        List<SurveyVO> getSurveyItemList = surveyMapper.selectQueItemList(surveyIndex);
//        return getSurveyItemList;
//    }
//
//    @Override
//    public int selectApplyCnt(SurveyVO userSurvey) throws Exception {
//        logger.info("ApplyCmt");
//        return surveyMapper.selectApplyCnt(userSurvey);
//    }
//
//    @Override
//    public void insertSurvey(SurveyVO surveyVO) {
//        logger.info("addSurvey");
//        surveyMapper.insertSurvey(surveyVO);
//        System.out.println("addsurvey 성공");
//
//    }


}
