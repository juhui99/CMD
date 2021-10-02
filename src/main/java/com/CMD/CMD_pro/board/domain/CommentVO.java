package com.CMD.CMD_pro.board.domain;

import java.util.Date;

public class CommentVO {
    private int bno;
    private int c_bno;
    private int c_sequence;
    private int level;
    private Date reg_date;
    private String writer;
    private String content;
    private int delete_option;
    private String real_date;

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public int getC_bno() {
        return c_bno;
    }

    public void setC_bno(int c_bno) {
        this.c_bno = c_bno;
    }

    public int getC_sequence() {
        return c_sequence;
    }

    public void setC_sequence(int c_sequence) {
        this.c_sequence = c_sequence;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDelete_option() {
        return delete_option;
    }

    public void setDelete_option(int delete_option) {
        this.delete_option = delete_option;
    }

    public String getReal_date() {
        return real_date;
    }

    public void setReal_date(String real_date) {
        this.real_date = real_date;
    }
}
