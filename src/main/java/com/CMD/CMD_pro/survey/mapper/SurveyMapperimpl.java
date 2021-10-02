package com.CMD.CMD_pro.survey.mapper;

import ch.qos.logback.core.joran.spi.ConsoleTarget;
import com.CMD.CMD_pro.survey.domain.*;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class SurveyMapperimpl implements SurveyMapper{
    private static final Logger logger = LoggerFactory.getLogger(SurveyMapperimpl.class);
    private String namespace = "com.CMD.CMD_pro.survey.mapper.SurveyMapper";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<SurveyVO> selectSurveyList(SearchCriteria cri) throws Exception {
        logger.info("selectSurveyList");

        List<SurveyVO> surveyList = sqlSession.selectList(namespace + ".selectSurveyList", cri);
        return surveyList;
    }

//    @Override
//    public PageMaker selectCountPaging(SearchCriteria cri) throws Exception{
//        logger.info("selectCountPaging");
//        PageMaker pageMaker = new PageMaker();
//        pageMaker.setCri(cri);
//        int totalCount = sqlSession.selectOne(namespace+".selectCountPaging", cri);
//        pageMaker.setTotalCount(totalCount);
//        return pageMaker;
//    }

    @Override
    public int selectCountPaging() throws Exception{
        return sqlSession.selectOne(namespace+".selectCountPaging");
    }

    // 아이템리스트가 포함된 select
    @Override
    public List<SurveyItemVO> selectSurveyItems(int survey_index) {
        logger.info("selectSurvey");
        List<SurveyItemVO> surveyItemList = null;
        surveyItemList = sqlSession.selectList(namespace+".selectSurveyItems", survey_index);
        return surveyItemList;
    }

    @Override
    public List<ResultDataSet> selectSurveyResultDataSet(int survey_index) {
        logger.info("selectSurveyResultDataSet");

        return sqlSession.selectList(namespace+".selectResultDataset", survey_index);
    }

    @Override
    public int resultCount(int survey_item_index){
        return sqlSession.selectOne(namespace+".resultCount", survey_item_index);
    }

    @Override
    public SurveyVO selectSurvey(int survey_index) {
        logger.info("selectSurvey");
        return sqlSession.selectOne(namespace+".selectSurvey", survey_index);
    }

    @Override
    public void insertSurvey(SurveyVO surveyVO) {
        logger.info("addSurvey");
        sqlSession.insert(namespace+".insertSurvey", surveyVO);
        System.out.println("addsurvey 성공");
    }

    @Override
    public SurveyItemVO selectMySurveyResult(int survey_index, int user_index) {
        logger.info("selecyMySurveyResult");
        Map<String, Integer> params = new HashMap<>();
        params.put("survey_seq", survey_index);
        params.put("member_seq", user_index);
        return sqlSession.selectOne(namespace+".selectMySurveyResult", params);
    }


    @Override
    public void insertSurveyItem(List<SurveyItemVO> itemlist) {
        logger.info("addSurveyItem");
        Iterator<SurveyItemVO> itemir = itemlist.iterator();
        while (itemir.hasNext()) {
            SurveyItemVO surveyItemVO = itemir.next();
//            surveyItemVO.setSurvey_index(surveyVO.getSurvey_index());
//            surveyItemVO.setSurvey_index(sqlSession.selectOne(namespace+".maxSurveyIndex"));

            int index = sqlSession.selectOne(namespace+".maxSurveyIndex");

            System.out.println(index);

            sqlSession.insert(namespace+".insertSurveyItem", surveyItemVO);

        }
        System.out.println("addsurveyitem 성공");
    }


    // 설문조사 보기 선택
    @Override
    public void insertSurveyResult(SurveyResultVO srvo) {
        logger.info("insertSurveyResult");
        sqlSession.insert(namespace+".insertSurveyResult", srvo);
    }


    @Override
    public void closeSurvey(int survey_index) {
        logger.info("closeSurvey");
        sqlSession.update(namespace+".closeSurvey", survey_index);
    }

    @Override
    public void removeSurvey(int survey_index) {
        logger.info("removeSurvey");
        sqlSession.delete(namespace+".removeSurvey", survey_index);
    }


}
