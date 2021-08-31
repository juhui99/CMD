package com.CMD.CMD_pro.survey.mapper;

import com.CMD.CMD_pro.survey.domain.*;
import com.CMD.CMD_pro.survey.service.serviceimpl.SurveyServiceimpl;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SurveyMapperimpl implements SurveyMapper{
    private static final Logger logger = LoggerFactory.getLogger(SurveyServiceimpl.class);
    private String namespace = "com.CMD.CMD_pro.board.mapper.SurveyMapper";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<SurveyVO> selectSurveyList(SearchCriteria cri) throws Exception {
        logger.info("selectSurveyList");

        List<SurveyVO> surveyList = sqlSession.selectList(namespace + ".selectSurveyList", cri);
        return surveyList;
    }

    @Override
    public PageMaker selectCountPaging(SearchCriteria cri) {
        logger.info("selectCountPaging");
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        int totalCount = sqlSession.selectOne(namespace+".selectCountPaging", cri);
        pageMaker.setTotalCount(totalCount);
        return pageMaker;
    }

    // 아이템리스트가 포함된 select
    @Override
    public List<SurveyItemVO> selectSurveyItems(int surveyIndex) {
        logger.info("selectSurvey");
        List<SurveyItemVO> surveyItemList = null;
        surveyItemList = sqlSession.selectList(namespace+".selectSurveyItems", surveyIndex);
        return surveyItemList;
    }

    @Override
    public List<ResultDataSet> selectSurveyResultDataSet(int surveyIndex) {
        logger.info("selectSurveyResultDataSet");
        return sqlSession.selectList(namespace+".selectResultDataset", surveyIndex);
    }

    @Override
    public SurveyVO selectSurvey(int surveyIndex) {
        logger.info("selectSurvey");
        return sqlSession.selectOne(namespace+".selectSurvey", surveyIndex);
    }

    @Override
    public void insertSurvey(SurveyVO surveyVO) {
        logger.info("addSurvey");
        sqlSession.insert(namespace+".insertSurvey", surveyVO);
        System.out.println("addsurvey 성공");
    }

    @Override
    public SurveyItemVO selecyMySurveyResult(int surveyIndex, int user_index) {
        logger.info("selecyMySurveyResult");
        Map<String, Integer> params = new HashMap<>();
        params.put("survey_seq", surveyIndex);
        params.put("member_seq", user_index);
        return sqlSession.selectOne(namespace+".selecyMySurveyResult", params);
    }

    @Override
    public void insertSurveyItem(List<SurveyItemVO> itemlist) {
        logger.info("addSurveyItem");
        Iterator<SurveyItemVO> itemir = itemlist.iterator();
        while (itemir.hasNext()) {
            SurveyItemVO surveyItemVO = itemir.next();
            sqlSession.insert(namespace+".insertSurveyItem", surveyItemVO);
        }
        System.out.println("addsurveyitem 성공");
    }

    // 설문조사 보기 선택
    @Override
    public void addSurveyResult(SurveyResultVO srvo) {
        logger.info("addSurveyResult");
        sqlSession.insert(namespace+".addSurveyResult", srvo);
    }

//    @Override
//    public List<SurveyVO> selectSearchSurvey(SearchCriteria cri) {
//        logger.info("selectSearchSurvey");
//        List<SurveyVO> list = sqlSession.selectList(namespace+".selectSearchSurvey", cri);
//        return list;
//    }

    @Override
    public void closeSurvey(int surveyIndex) {
        logger.info("closeSurvey");
        sqlSession.update(namespace+".closeSurvey", surveyIndex);
    }

    @Override
    public void removeSurvey(int surveyIndex) {
        logger.info("removeSurvey");
        sqlSession.delete(namespace+".removeSurvey", surveyIndex);
    }

//    public void mainSearch(HttpServletRequest request, String keyword) throws Exception {
//        String keyWord = "non";
//        if (request.getParameter("keyword") != null){
//            keyWord = request.getParameter("keyword");
//        }
//    }
//
//    @Override
//    public int mainSearchCount(String keyword) throws Exception {
//        return 0;
//    }

}
