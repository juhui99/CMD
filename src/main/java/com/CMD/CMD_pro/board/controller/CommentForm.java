package com.CMD.CMD_pro.board.controller;

public class CommentForm {
    private int board_bno;
    private String comment_writer;
    private String commentContent;
    private int c_sequence;
    private String board_kind;
    private String board_realm;
    private int c_bno;
    private String pageNumber;
    private String option;

    public int getBoard_bno() {
        return board_bno;
    }

    public void setBoard_bno(int board_bno) {
        this.board_bno = board_bno;
    }

    public String getComment_writer() {
        return comment_writer;
    }

    public void setComment_writer(String comment_writer) {
        this.comment_writer = comment_writer;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getC_sequence() {
        return c_sequence;
    }

    public void setC_sequence(int c_sequence) {
        this.c_sequence = c_sequence;
    }

    public String getBoard_kind() {
        return board_kind;
    }

    public void setBoard_kind(String board_kind) {
        this.board_kind = board_kind;
    }

    public String getBoard_realm() {
        return board_realm;
    }

    public void setBoard_realm(String board_realm) {
        this.board_realm = board_realm;
    }

    public int getC_bno() {
        return c_bno;
    }

    public void setC_bno(int c_bno) {
        this.c_bno = c_bno;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
