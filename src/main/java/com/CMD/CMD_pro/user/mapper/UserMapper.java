package com.CMD.CMD_pro.user.mapper;

import com.CMD.CMD_pro.user.domain.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    public void userJoin(UserVO user) throws Exception;
    public int idChk(String userID) throws Exception;
    public UserVO userLogin(String userID) throws Exception;
    public void userUpdate(UserVO user) throws Exception;
    public void userProfile(@Param("userProfile") String userProfile, @Param("userID") String userID) throws Exception;
    public void userWithdrawal(@Param("userID") String userID) throws Exception;
    public UserVO userData(String userID) throws Exception;
}
