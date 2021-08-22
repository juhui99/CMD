package com.CMD.CMD_pro.chat.mapper;

import com.CMD.CMD_pro.board.domain.BoardVO;
import com.CMD.CMD_pro.chat.domain.ChatVO;
import com.CMD.CMD_pro.user.domain.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMapper {

    public int checkFriend(@Param("user_id") String user_id, @Param("friend_id") String friend_id) throws Exception;
    public void addFriend(@Param("user_id") String user_id, @Param("friend_id") String friend_id) throws Exception;
    public List<UserVO> friendsList(@Param("user_id") String user_id) throws Exception;
    public List<UserVO> friendsSearch(@Param("user_id") String user_id, @Param("keyword") String keyword) throws Exception;
    public void deleteFriend(@Param("user_id") String user_id, @Param("friend_id") String friend_id) throws Exception;
    public void submitChat(ChatVO chat) throws Exception;
    public List<ChatVO> chatList(@Param("user_id") String user_id, @Param("friend_id") String friend_id, @Param("last_index") int last_index) throws Exception;
    public List<ChatVO> chatBox(@Param("user_id") String user_id) throws Exception;
}
