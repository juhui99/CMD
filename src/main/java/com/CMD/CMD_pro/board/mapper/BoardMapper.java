package com.CMD.CMD_pro.board.mapper;


import com.CMD.CMD_pro.board.domain.BoardVO;
import com.CMD.CMD_pro.board.domain.CommentVO;
import com.CMD.CMD_pro.board.domain.FileVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {
    public void boardInsert(BoardVO board) throws Exception;

    public List<BoardVO> boardList(@Param("off") int off, @Param("kind") String kind, @Param("realm") String realm) throws Exception;

    public BoardVO boardView(@Param("bno") int bno, @Param("kind") String kind, @Param("realm") String realm) throws Exception;

    public void boardUpdate(BoardVO board) throws Exception;

    public void boardDelete(@Param("bno") int bno, @Param("kind") String kind, @Param("realm") String realm, @Param("writer") String writer) throws Exception;

    public List<BoardVO> boardSearch(@Param("search_option") String search_option, @Param("keyword") String keyword, @Param("kind") String kind, @Param("realm") String realm, @Param("off") int off) throws Exception;

    public void boardHit(int bno) throws Exception;

    public int targetPage(@Param("target") int target) throws Exception;

    public int boardCount(@Param("kind") String kind, @Param("realm") String realm) throws Exception;

    public int searchCount(@Param("search_option") String search_option, @Param("keyword") String keyword, @Param("kind") String kind, @Param("realm") String realm) throws Exception;

    public int recommendCheck(@Param("kind") String kind, @Param("realm") String realm, @Param("bno") int bno, @Param("userID") String userID) throws Exception;

    public void recommendAdd(int bno) throws Exception;

    public void recommendSubtract(int bno) throws Exception;

    public void recommendInsert(@Param("kind") String kind, @Param("realm") String realm, @Param("bno") int bno, @Param("userID") String userID) throws Exception;

    public void recommendDelete(@Param("kind") String kind, @Param("realm") String realm, @Param("bno") int bno, @Param("userID") String userID) throws Exception;

    public List<BoardVO> HotTopicList(@Param("kind") String kind, @Param("realm") String realm) throws Exception;

    public int selectMax();

    public void fileInsert(FileVO file) throws Exception;

    public FileVO fileDetail(@Param("bno") int bno) throws Exception;

    public void fileDelete(@Param("bno") int bno) throws Exception;

    public void fileUpdate(FileVO file) throws Exception;

    public List<CommentVO> CommentList(@Param("bno") int bno) throws Exception;

    public int CommentCount(@Param("bno") int bno) throws Exception;

    public void CommentInsert(CommentVO comment) throws Exception;

    public void ReplyInsert(CommentVO comment) throws Exception;

    public void CommentUpdate(@Param("commentBno") int commentBno, @Param("commentContent") String commentContent) throws Exception;

    public void ReplyDelete(@Param("commentBno") int commentBno) throws Exception;

    public void CommentDelete(@Param("commentSequence") int commentSequence) throws Exception;

    public void CommentSetDelete(@Param("bno") int bno) throws Exception;

    public List<BoardVO> TotalHotTopicList(@Param("realm") String realm) throws Exception;


}