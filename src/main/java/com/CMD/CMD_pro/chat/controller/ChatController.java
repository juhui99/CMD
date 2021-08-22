package com.CMD.CMD_pro.chat.controller;

import com.CMD.CMD_pro.chat.domain.ChatVO;
import com.CMD.CMD_pro.chat.mapper.ChatMapper;
import com.CMD.CMD_pro.user.domain.UserVO;
import com.CMD.CMD_pro.user.mapper.UserMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

@Controller
public class ChatController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ChatMapper chatMapper;

    @GetMapping("/friendsList")
    public String friendsList(HttpSession session, Model model) throws Exception {
        if(session.getAttribute("id") == null){
            model.addAttribute("msg","로그인이 되어있지 않습니다.");
            model.addAttribute("url","login");
            return "alert";
        }

        String userID = (String) session.getAttribute("id");
        UserVO user = userMapper.userLogin(userID);
        String filename;
        if (user.getUser_profile() == null) {
            filename = "non";
        } else {
            filename = user.getUser_profile();
        }
        List<UserVO> friendsList = chatMapper.friendsList(userID);
        model.addAttribute("friendsList",friendsList);
        model.addAttribute("filename", filename);
        return "friendsList";

    }

    @GetMapping("/friendsFind")
    public String friendsFind(HttpSession session, Model model) throws Exception {
        if(session.getAttribute("id") == null){
            model.addAttribute("msg","로그인이 되어있지 않습니다.");
            model.addAttribute("url","login");
            return "alert";
        }

        String userID = (String) session.getAttribute("id");
        UserVO user = userMapper.userLogin(userID);
        String filename;
        if (user.getUser_profile() == null) {
            filename = "non";
        } else {
            filename = user.getUser_profile();
        }
        model.addAttribute("filename", filename);
        return "friendsFind";

    }

    @ResponseBody
    @RequestMapping(value = "/findFriend", method = RequestMethod.POST)
    public String findFriend(HttpServletRequest req) throws Exception{
        UserVO user = null;
        String user_id = req.getParameter("id");
        if(userMapper.userLogin(user_id) != null ){
            user = userMapper.userLogin(user_id);
            String userProfile = user.getUser_profile();
            if(userProfile == null){
                return "default";
            }
            else {
                return userProfile;
            }
        }
        return "error";
    }

    @ResponseBody
    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    public int addFriend(HttpServletRequest req, HttpSession session) throws Exception{
        String userId = (String) session.getAttribute("id");
        String friendId = req.getParameter("friend");
        int checkFriend = chatMapper.checkFriend(userId,friendId);
        if (checkFriend == 1){
            return 1;
        } else {
            chatMapper.addFriend(userId,friendId);
            return 0;
        }
    }

    @GetMapping("/friendDelete")
    public String friendDelete(@RequestParam("user") String user,@RequestParam("friend") String friend, HttpSession session, Model model) throws Exception{
        chatMapper.deleteFriend(user,friend);
        return "redirect:/friendsList";
    }

    @PostMapping("/search_myFriend")
    public String searchMyFriend(FriendSearchForm form,HttpSession session, Model model) throws Exception{
        if(session.getAttribute("id") == null){
            model.addAttribute("msg","로그인이 되어있지 않습니다.");
            model.addAttribute("url","login");
            return "alert";
        }
        String keyword = form.getKeyword();
        String userID = (String) session.getAttribute("id");
        UserVO user = userMapper.userLogin(userID);
        String filename;
        if (user.getUser_profile() == null) {
            filename = "non";
        } else {
            filename = user.getUser_profile();
        }
        List<UserVO> friendsList = chatMapper.friendsSearch(userID,keyword);
        model.addAttribute("friendsList",friendsList);
        model.addAttribute("filename", filename);
        return "friendsList";
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("friend") String friend, HttpSession session,Model model) throws Exception{
        if(session.getAttribute("id") == null){
            model.addAttribute("msg","로그인이 되어있지 않습니다.");
            model.addAttribute("url","login");
            return "alert";
        }
        String userID = (String) session.getAttribute("id");
        UserVO user = userMapper.userLogin(userID);
        UserVO friendUser = userMapper.userLogin(friend);
        String filename;
        String friendFile;
        if (user.getUser_profile() == null) {
            filename = "non";
        } else {
            filename = user.getUser_profile();
        }
        friendFile = friendUser.getUser_profile();

        model.addAttribute("filename", filename);
        model.addAttribute("friend",friend);
        model.addAttribute("friendFile",friendFile);
        return "chat";
    }

    @ResponseBody
    @RequestMapping(value = "/submitChat", method = RequestMethod.POST)
    public void submitChat(HttpServletRequest req, HttpSession session) throws Exception{
        req.setCharacterEncoding("UTF-8");
        String userId = (String) session.getAttribute("id");
        String friendId = req.getParameter("friend");
        String chatContent = req.getParameter("content");
        ChatVO chat = new ChatVO();
        chat.setUser_id(userId);
        chat.setFriend_id(friendId);
        chat.setChat_content(chatContent);
        chatMapper.submitChat(chat);

    }


    @ResponseBody
    @RequestMapping(value = "/getChatList", method = RequestMethod.POST)
    public void getChatList(HttpServletRequest req, HttpSession session, HttpServletResponse response) throws Exception{
        req.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String friendID = req.getParameter("friend");
        int chatID = Integer.parseInt(req.getParameter("id"));
        String userID = (String) session.getAttribute("id");
        List<ChatVO> chatList = chatMapper.chatList(userID,friendID,chatID);
        if(chatList.size() == 0){
            try{
                response.getWriter().print("");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else {
            for (int i = 0; i < chatList.size(); i++) {
                chatList.get(i).setChat_content(chatList.get(i).getChat_content().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
            }
            StringBuffer result = new StringBuffer("");
            result.append("{\"result\":[");
            for (int i = 0; i < chatList.size(); i++) {
                result.append("[{\"value\": \"" + chatList.get(i).getUser_id() + "\"},");
                result.append("{\"value\": \"" + chatList.get(i).getChat_content() + "\"},");
                result.append("{\"value\": \"" + chatList.get(i).getChat_time() + "\"}]");
                if (i != chatList.size() - 1) result.append(",");

            }
            result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChat_index() + "\"}");
            chatMapper.readChat(userID,friendID);
            try {
                response.getWriter().print(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @GetMapping("/chatBox")
    public String chatBox(HttpSession session,Model model) throws Exception{
        if(session.getAttribute("id") == null){
            model.addAttribute("msg","로그인이 되어있지 않습니다.");
            model.addAttribute("url","login");
            return "alert";
        }

        String userID = (String) session.getAttribute("id");
        UserVO user = userMapper.userLogin(userID);
        String filename;
        if (user.getUser_profile() == null) {
            filename = "non";
        } else {
            filename = user.getUser_profile();
        }
        model.addAttribute("filename", filename);
        return "chatBox";
    }

    @ResponseBody
    @RequestMapping(value = "/getChatBox", method = RequestMethod.POST)
    public void getChatBox(HttpServletRequest req, HttpSession session, HttpServletResponse response) throws Exception{
        req.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String userID = req.getParameter("id");
        List<ChatVO> chatList = chatMapper.chatBox(userID);
        if(chatList.size() == 0){
            try{
                response.getWriter().print("");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else {
            for (int i = 0; i < chatList.size(); i++) {
                chatList.get(i).setChat_content(chatList.get(i).getChat_content().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
            }
            for(int i =0; i<chatList.size(); i++) {
                ChatVO x = chatList.get(i);
                for(int j=0; j<chatList.size(); j++) {
                    ChatVO y = chatList.get(j);
                    if(x.getUser_id().equals(y.getFriend_id()) && x.getFriend_id().equals(y.getUser_id())) {
                        if(x.getChat_index() < y.getChat_index()) {
                            chatList.remove(x);
                            i--;
                            break;
                        } else {
                            chatList.remove(y);
                            j--;
                        }
                    }
                }
            }



            String userProfile = "";

            StringBuffer result = new StringBuffer("");
            result.append("{\"result\":[");
            for (int i = 0; i < chatList.size(); i++) {
                String unread = "";

                if(chatList.get(i).getUser_id().equals(userID)){
                    userProfile = chatMapper.getProfile(chatList.get(i).getFriend_id());
                } else{
                    userProfile = chatMapper.getProfile(chatList.get(i).getUser_id());
                }
                if(userID.equals(chatList.get(i).getFriend_id())) {
                    unread = chatMapper.getUnreadChat(chatList.get(i).getUser_id(), userID) + "";
                    if(unread.equals("0")) unread = "";
                }

                result.append("[{\"value\": \"" + chatList.get(i).getUser_id() + "\"},");
                result.append("{\"value\": \"" + chatList.get(i).getFriend_id() + "\"},");
                result.append("{\"value\": \"" + chatList.get(i).getChat_content() + "\"},");
                result.append("{\"value\": \"" + chatList.get(i).getChat_time() + "\"},");
                result.append("{\"value\": \"" + userProfile + "\"},");
                result.append("{\"value\": \"" + unread + "\"}]");
                if (i != chatList.size() - 1) result.append(",");
            }
            result.append("]}");
            try {
                response.getWriter().print(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



}
