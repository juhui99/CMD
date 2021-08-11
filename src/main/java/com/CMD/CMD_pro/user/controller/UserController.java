package com.CMD.CMD_pro.user.controller;

import com.CMD.CMD_pro.user.domain.UserVO;
import com.CMD.CMD_pro.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/JoinAction")
    public String joinAction(JoinForm form) throws Exception{
        UserVO user = new UserVO();
        user.setUser_id(form.getUser_id());
        user.setUser_pwd(form.getUser_pwd());
        user.setUser_name(form.getUser_name());
        user.setUser_age(form.getUser_age());
        user.setUser_major(form.getUser_major());
        user.setUser_email(form.getUser_email());
        user.setUser_gender(form.getUser_gender());
        userMapper.userJoin(user);
        return "redirect:/join";
    }

    @ResponseBody
    @RequestMapping(value = "/idChk", method = RequestMethod.POST)
    public int idChk(HttpServletRequest req) throws Exception{
        String user_id = req.getParameter("id");
        int result = userMapper.idChk(user_id);
        return result;
    }

    @GetMapping("/login")
    public String login(){ return "login"; }

    @PostMapping("/LoginAction")
    public String loginAction(Model model, JoinForm form, HttpServletRequest request)throws Exception {
        String userID = form.getUser_id();
        String userPassword = form.getUser_pwd();
        System.out.println(userID);
        UserVO user = userMapper.userLogin(userID);
        if(user != null){
            if(user.getUser_pwd().equals(userPassword)){
                HttpSession session = request.getSession();
                session.setAttribute("id",userID);
                return "cmdev";
            }
            model.addAttribute("msg","비밀번호가 틀립니다.");
            model.addAttribute("url","login");
            return "alert";
        }

        model.addAttribute("msg","존재하지 않는 아이디입니다.");
        model.addAttribute("url","login");
        return "alert";
    }


    @GetMapping("/logoutAction")
    public String LogoutAction(HttpSession session){
        session.invalidate();
        return "cmdev";
    }

    @GetMapping("/userUpdateCheck")
    public String userUpdateCheck(){
        return "userUpdateCheck";
    }

    @PostMapping("/userUpdate")
    public String userUpdate(HttpSession session,JoinForm form,Model model) throws Exception {
        if(session.getAttribute("id") == null){
            model.addAttribute("msg","로그인이 되어있지 않습니다.");
            model.addAttribute("url","main");
            return "alert";
        }
        String userID = (String) session.getAttribute("id");
        String userPassword = form.getUser_pwd();
        UserVO user = userMapper.userLogin(userID);
        if (user.getUser_pwd().equals(userPassword)) {
            model.addAttribute("user",user);
            return "userUpdate";
        }
        else {
            model.addAttribute("msg","비밀번호가 일치하지 않습니다.");
            model.addAttribute("url","userUpdateCheck");
            return "alert";
        }
    }

    @PostMapping("/userUpdateAction")
    public String userUpdateAction(Model model,JoinForm form) throws Exception{
        UserVO user = new UserVO();
        user.setUser_id(form.getUser_id());
        user.setUser_pwd(form.getUser_pwd());
        user.setUser_name(form.getUser_name());
        user.setUser_age(form.getUser_age());
        user.setUser_major(form.getUser_major());
        user.setUser_email(form.getUser_email());
        user.setUser_gender(form.getUser_gender());
        userMapper.userUpdate(user);
        model.addAttribute("msg","회원정보가 수정되었습니다.");
        model.addAttribute("url","main");
        return "alert";
    }

}
