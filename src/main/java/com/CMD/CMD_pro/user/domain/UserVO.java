package com.CMD.CMD_pro.user.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
    private int user_index;
    private String user_id;
    private String user_pwd;
    private String user_name;
    private int user_age;
    private String user_major;
    private String user_email;
    private String user_gender;
    private String user_profile;
    private int user_manager;
    private int user_withdrawal;
}
