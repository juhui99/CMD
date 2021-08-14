package com.CMD.CMD_pro.user.controller;

import com.CMD.CMD_pro.board.controller.WriteForm;
import com.CMD.CMD_pro.board.domain.BoardVO;
import com.CMD.CMD_pro.board.domain.FileVO;
import com.CMD.CMD_pro.user.domain.UserVO;
import com.CMD.CMD_pro.user.mapper.UserMapper;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
                String filename;
                if((String) session.getAttribute("id") != null){
                    userID = (String) session.getAttribute("id");
                    if(user.getUser_profile() == null){
                        filename = "non";
                    }
                    else{
                        filename = user.getUser_profile();
                    }
                } else {
                    filename = "non";
                }

                model.addAttribute("filename",filename);
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
    public String LogoutAction(HttpSession session, Model model) throws Exception{
        session.invalidate();

        String filename = "non";

        model.addAttribute("filename",filename);
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

    @GetMapping("/display")
    public ResponseEntity<Resource> display(@Param("filename") String filename){
        String path = "C:\\images\\";
        Resource resource = new FileSystemResource(path + filename);
        HttpHeaders header = new HttpHeaders();
        Path filePath = null;
        try{
            filePath = Paths.get(path + filename);
            header.add("Content-Type", Files.probeContentType(filePath));
        } catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity<Resource>(resource,header, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(HttpSession session, Model model) throws Exception{
        String userID;
        String filename;
        if((String) session.getAttribute("id") != null){
            userID = (String) session.getAttribute("id");
            UserVO user = userMapper.userLogin(userID);
            if(user.getUser_profile() == null){
                filename = "non";
            }
            else{
                filename = user.getUser_profile();
            }
        } else {
            filename = "non";
        }

        model.addAttribute("filename",filename);
        return "test";
    }

    @GetMapping("/profile")
    public String getProfile(){ return "profile";}

    @PostMapping("/profile")   //게시물 리스트 가져오기 post방식 글쓰기 완료후 폼 액션
    public String WriteAction(WriteForm form, RedirectAttributes redirect, HttpSession session, Model model, @RequestPart MultipartFile files) throws Exception{
        String userID = (String) session.getAttribute("id");
        String fileName = files.getOriginalFilename();
        String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
        File destinationFile;
        String destinationFileName;
        String fileUrl = "C:\\images\\";
        do{
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
            destinationFile = new File(fileUrl + destinationFileName);

        } while (destinationFile.exists());
        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile);
        userMapper.userProfile(destinationFileName,userID);

        redirect.addAttribute("kind", form.getKind());
        redirect.addAttribute("realm", form.getRealm());
        return "login";
    }


}
