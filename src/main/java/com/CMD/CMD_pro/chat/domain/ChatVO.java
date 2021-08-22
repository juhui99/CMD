package com.CMD.CMD_pro.chat.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ChatVO {
    private int chat_index;
    private String user_id;
    private String friend_id;
    private String chat_content;
    private Date chat_time;
    private int chat_read;
}
