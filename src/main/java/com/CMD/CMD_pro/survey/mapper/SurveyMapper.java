package com.CMD.CMD_pro.survey.mapper;

import com.CMD.CMD_pro.board.domain.BoardVO;
import com.CMD.CMD_pro.survey.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface SurveyMapper {
    public List<SurveyVO> selectSurveyList(SearchCriteria cri) throws Exception;
    public PageMaker selectCountPaging(SearchCriteria cri);
    public List<ResultDataSet> selectSurveyResultDataSet(int survey_index);
    public List<SurveyItemVO> selectSurveyItems(int survey_index);
    public SurveyVO selectSurvey(int survey_index);
    public SurveyItemVO selecyMySurveyResult(int survey_index, int user_index);
    public void insertSurvey(SurveyVO svo);
    public void insertSurveyItem(List<SurveyItemVO> itemlist);
    public void addSurveyResult(SurveyResultVO srvo);
//    public List<SurveyVO> selectSearchSurvey(SearchCriteria cri);
    public void closeSurvey(int survey_index);
    public void removeSurvey(int survey_index);
//    public void searchInsert(@Param("keyword") String keyword) throws Exception;
//    public List<SurveyVO> mainSearch(@Param("keyword") String keyword) throws Exception;
//    public int targetPage(@Param("target") int target) throws Exception;
//    public List<SurveyVO> mainSearch(@Param("keyword") String keyword) throws Exception;
//    public int mainSearchCount(@Param("keyword") String keyword) throws Exception;


//    //설문조사 리스트
//    List<SurveyVO> selectSurveyArticleList(SurveyVO surveyVO) throws Exception;
//    //설문조사 리스트CNT
//    int selectSurveyArticleListCnt(SurveyVO surveyVO) throws Exception;
//    //설문조사 등록
//    void insertSurvey(SurveyVO surveyVO) throws Exception;
//    //설문조사 글보기(조회)
//    SurveyVO getSurvey(int que_id) throws Exception;
//    //설문조사 글수정
//    void updateSurvey(SurveyVO surveyVO) throws Exception;
//    //설문조사 글삭제
//    void deleteSurvey(int que_id) throws Exception;
//    //설문조사 글삭제(해당 글에서 작성한 질문 삭제)
//    void deleteSurveyQueAll(int que_id);
//    //설문조사 글삭제(해당 질문에서 작성한 답변 항목 삭제)
//    void deleteSurveyQueItemAll(int que_id);
//
//    //설문조사 질문리스트
//    List<SurveyVO> selectAnQueList(SurveyVO surveyVO) throws Exception;
//    //설문조사 질문리스트 count
//    int selectAnQueListCnt(SurveyVO survey) throws Exception;
//    //설문조사 질문 등록
//    void insertSurveyQue(SurveyVO surveyVO) throws Exception;
//    //설문조사 질문 조회
//    SurveyVO getSurveyQue(int anque_id) throws Exception;
//    //설문조사 질문 수정
//    void updateSurveyQue(SurveyVO surveyVO) throws Exception;
//    //설문조사 질문 삭제
//    void deleteSurveyQue(int anque_id) throws Exception;
//
//    //설문조사 답변 항목리스트(전체)
//    List<SurveyVO> selectQueItemList(int que_id) throws Exception;
//    //설문조사 답변 항목리스트(단일)
//    List<SurveyVO> selectAnQueItemList(int que_id) throws Exception;
//    //설문조사 답변 항목 등록
//    void insertSurveyQueItem(SurveyVO surveyVO) throws Exception;
//    //설문조사 답변 항목 삭제
//    void deleteSurveyQueItem(int anque_id) throws Exception;
//    //설문조사 답변 항목 idx max값 조회
//    int getSurveyQueItemIdx(int anque_id) throws Exception;
//    //설문조사 답변 항목 완전 삭제
//    void deleteSurveyQueItemReal(int anque_id) throws Exception;
//
//    //설문조사 결과 리스트
//    List<SurveyVO> selectSurveyResultArticleList(SurveyVO surveyVO) throws Exception;
//    //설문조사 결과 리스트CNT
//    int selectSurveyResultArticleListCnt(SurveyVO surveyVO) throws Exception;
//    //설문조사 결과 등록(참여자)
//    int insertSurveyUser(SurveyVO surveyVO) throws Exception;
//    //설문조사 결과 등록(설문 내용)
//    void insertSurveyResult(SurveyVO surveyVO) throws Exception;
//    //설문조사 결과 결과보기(참여자 정보)
//    SurveyVO getSurveyResultUser(int userinfo_id) throws Exception;
//
//    //설문조사 결과 답변 항목 가져오기
//    List<SurveyVO> selectAnQueItemResult(int que_id) throws Exception;
//
//    //설문조사 중복 설문 체크
//    int selectSurveyUser(SurveyVO userSurvey) throws Exception;
//
//    SurveyVO selectAnswerStat(SurveyVO surveyVO) throws Exception;
//
//    int getAnswerCount(int anque_id) throws Exception;
//    int getQuestionResultCount(int itemque_id) throws Exception;
//    String getSubjectAnswer(int itemque_id) throws Exception;
//    int selectApplyCnt(SurveyVO userSurvey) throws Exception;

}
