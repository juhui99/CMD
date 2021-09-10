package com.CMD.CMD_pro.survey.controller;

import com.CMD.CMD_pro.board.controller.UpdateForm;
import com.CMD.CMD_pro.survey.domain.*;
import com.CMD.CMD_pro.survey.mapper.SurveyMapper;
import com.CMD.CMD_pro.user.domain.UserVO;
import com.CMD.CMD_pro.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/survey/*") //현재 믈래스의 모든 메서드들의 기본적인 URL경로
public class SurveyController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SurveyMapper surveyMapper;

    @RequestMapping(value = "/mainSurvey") //설문조사 메인화면
    public String main(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

        List<SurveyVO> surveyList = surveyMapper.selectSurveyList(cri);
        model.addAttribute("surveyList", surveyList);//survey 리스트 출력

        PageMaker pageMaker = surveyMapper.selectCountPaging(cri);
        model.addAttribute("pageMaker", pageMaker); // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지

        //model.addAttribute("list", surveyList.get(0)); //데이터 전달 test 확인
        return "mainSurvey";
    }

    @RequestMapping(value = "/readSurvey") //진행중인 설문조사 읽기
    public String readSurvey(@RequestParam("survey_index") int survey_index, @RequestParam("progressing") int progressing,
                             @ModelAttribute("cri") SearchCriteria cri, Model model, HttpSession session) throws Exception{
        String userID = (String)session.getAttribute("id");
        if(userID == null){ //로그인 확인
            model.addAttribute("message","로그인이 되어있지 않습니다.");
            model.addAttribute("url","login");
            return "alert";
        }

        boolean isProgressing = progressing == 1 ? true : false;
        List<SurveyItemVO> surveyItemList = null;
        if(isProgressing) { //설문조사가 실행중일때
            surveyItemList = surveyMapper.selectSurveyItems(survey_index);
            model.addAttribute("surveyItemList", surveyItemList);//진행중인 설문조사 상세 페이지

            return "readSurvey_on";
        }
        else{ //설문조사 마감일때
            //surveyItemList = surveyMapper.selectSurveyResultDataSet(survey_index);
            List<SurveyItemVO> itemList = ((SurveyWithItemVO)surveyItemList).getSurveyItemList();

            model.addAttribute("surveyItemList", surveyItemList); //설문조사 보기
            model.addAttribute("itemList", itemList); //내용 리스트
            model.addAttribute("message","마감된 설문조사입니다.");

            return "readSurvey_off";
        }
    }

    @RequestMapping("/closeSurvey") //설문조사 닫기
    public String closeSurvey(@RequestParam("survey_index") int survey_index) {
        try {
            surveyMapper.closeSurvey(survey_index);
        } catch (Exception e) {
            return "redirect:/survey/main?surveyclose=fail"; //닫는거 실패했을때
        }

        return "redirect:/survey/main?surveyclose=success"; //설문조사 닫기 성공
    }

    @RequestMapping(value="/addSurvey",method = RequestMethod.GET)
    public String AddSurveyGET() throws Exception {
        return "addSurvey";
    }

    @RequestMapping(value="addSurvey", method = RequestMethod.POST) //설문조사 추가하기
    public String addSurvey(@RequestParam("survey_title") String survey_title, @RequestParam("survey_content") String survey_content,
                            @RequestParam("itemcontent") String [] itemcontent, @RequestParam("survey_end") String survey_end,
                            @RequestParam("writer") String writer, HttpServletRequest request, Model model, HttpSession session) throws Exception {
        SurveyVO surveyVO = new SurveyVO();
        SurveyWithItemVO surveyWithItemVO = new SurveyWithItemVO();
        String userID = (String)session.getAttribute("id");
        if(userID == null){ //로그인 확인
            model.addAttribute("message","로그인이 되어있지 않습니다.");
            model.addAttribute("url","login");
            return "alert";
        }
        UserVO user = userMapper.userLogin(userID);
        if(!userID.equals(writer) && user.getUser_manager()==0){ //매니저만 추가가능
            model.addAttribute("message","접근할 수 없는 권한입니다.");
            return "alert";
        }

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        surveyVO.setSurvey_end(sdf.parse(survey_end));
        surveyVO.setSurvey_title(survey_title);
        surveyVO.setSurvey_content(survey_content);
        surveyVO.setUser_index(user.getUser_index());
        List<SurveyItemVO> surveyItemList = new ArrayList<>();
        for (int i = 0; i < itemcontent.length; i++) {
            SurveyItemVO temp  = new SurveyItemVO();
            temp.setSurvey_item_content(itemcontent[i]);
            surveyItemList.add(temp);
        }
        surveyWithItemVO.setSurveyItemList(surveyItemList);
//        surveyMapper.addSurveyResult(surveyWithItemVO);

        return "redirect:/mainSurvey";
    }

    @RequestMapping(value="voteSurvey", method = RequestMethod.POST) //설문조사 참여하기
    public @ResponseBody Map<String, Object> addSurveyResult
            (@RequestParam("survey_item_index") int survey_item_index, @RequestParam("survey_index") int survey_index) {
        SurveyResultVO surveyResultVO = new SurveyResultVO();
        Map<String, Object> return_param = new HashMap<>();
        try {
            surveyResultVO.setSurvey_item_index(survey_item_index);
            //surveyResultVO.setMember_seq(user.getMember_seq());
            surveyResultVO.setSurvey_index(survey_index);
            surveyMapper.addSurveyResult(surveyResultVO);
            return_param.put("result", true);
            return_param.put("message", "설문에 참여하였습니다.");
        } catch (Exception e) {
            return_param.put("result", false);
            return_param.put("message", "이미 설문에 참여하였습니다.");
            return return_param;
        }
        return return_param;
    }

    @RequestMapping("/removeSurvey") //설문조사 지우기
    public String removeSurvey(@RequestParam("survey_index") int survey_index, @RequestParam("writer") String writer,
                               UpdateForm form, Model model, HttpSession session) throws Exception{
        String userID = (String)session.getAttribute("id");
        if(userID == null){ //로그인 확인
            model.addAttribute("message","로그인이 되어있지 않습니다.");
            model.addAttribute("url","login");
            return "alert";
        }
        UserVO user = userMapper.userLogin(userID);
        if(!userID.equals(writer) && user.getUser_manager()==0){ //매니저만 설문 삭제 가능
            model.addAttribute("message","접근할 수 없습니다.");
            return "alert";
        }
        try {
            surveyMapper.removeSurvey(survey_index);
            //model.addAttribute("msg","설문조사가 삭제되었습니다.");
        } catch (Exception e) {
            return "redirect:/survey/main?surveyremove=fail";
        }
        return "redirect:/survey/main?surveyremove=success";
    }

    @RequestMapping("/searchSurvey") // 메인 검색
    public String searchSurvey(Model model, @RequestParam("keyword") String keyword) throws Exception {
        SearchCriteria cri = new SearchCriteria();
        cri.setPage(1);
        cri.setPerPageNum(10);

        PageMaker pageMaker = new PageMaker();
        pageMaker.makeSearch(keyword);

        List<SurveyVO> SurveyList = surveyMapper.selectSurveyList(cri);
//        surveyMapper.searchInsert(keyword);
        model.addAttribute("keyword",keyword); //키워드 전달
        model.addAttribute("surveysearch",SurveyList); //검색한 설문 리스트
        return "searchSurvey";
    }

//    @RequestMapping(value = "/mainsurvey")
//    public String main(HttpServletRequest request, Model model) throws Exception{
//        UserVO user = (UserVO) request.getAttribute("UserVO");
//
//        if (user == null){
//            return "redirect:/login";//로그인 위치
//        }
//        List<SurveyVO> surveyList = surveyService.getSurveyList();
//        model.addAttribute("surveyList",surveyList);
//        return "index";
//    }

//    @RequestMapping(value = "/surveyView")
//    public String surveyView(HttpServletRequest request, SurveyVO surveyVO, Model model)throws Exception {
//        SurveyVO survey = surveyService.getSurvey(surveyVO.getSurveyIndex());
//        request.setCharacterEncoding("UTF-8");
//
//        SurveyVO useSurvey = new SurveyVO();
//        useSurvey.setUserIndex(surveyVO.getUserIndex());
//        useSurvey.setSurveyIndex(surveyVO.getSurveyIndex());
//
//        List<SurveyVO> surveyVOList = surveyService.getSurveyList(survey);
//        //int anQuestionResultCnt = surveyService.selectAnQueListCnt(survey);
//
//        //설문조사 답변 항목 가져오기
//        List<SurveyVO> surveyItemList = surveyService.getSurveyItemList(surveyVO.getSurveyIndex());
//
//        //설문조사 참여인원 가져오기
//        int applyCnt = surveyService.selectApplyCnt(useSurvey);
//
//        model.addAttribute("queListCnt", anQuestionResultCnt);
//        model.addAttribute("surveyList", surveyVOList);
//        model.addAttribute("surveyItemList", surveyItemList);
//        model.addAttribute("surveyIndex", surveyVO.getSurveyIndex());
//        model.addAttribute("survey", survey);
//        model.addAttribute("applyCnt", applyCnt);
//        model.addAttribute("name", "survey");
//        return "redirect:/surveyView";
//    }
}
