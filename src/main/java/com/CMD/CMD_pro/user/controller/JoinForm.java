package com.CMD.CMD_pro.user.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinForm {
    private String user_id;
    private String user_pwd;
    private String user_name;
    private int user_age;
    private String user_major;
    private String user_email;
    private String user_gender;
    private String modify;
    private String default_img;
}