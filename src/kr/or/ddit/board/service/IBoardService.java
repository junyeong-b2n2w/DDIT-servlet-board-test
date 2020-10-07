package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardService {
	//전체리스트
	public List<BoardVO> selectAll();
	//페이지별 리스트 -
  	public List<BoardVO> selectByPage(Map<String, Integer> map);

  	//전체글 개수
  	public int boardCount();
	//글쓰기
  	public int insertBoard(BoardVO vo);
	//글 수정하기  
  	public int updateBoard(BoardVO vo);
	//글 삭제
  	public int deleteBoard(int bonum);

	//댓글저장
  	public int replySave(ReplyVO vo);
	
  	//댓글리스트
  	public List<ReplyVO> replyList(int bonum);
  	//댓글수정
  	public int replyUpdate(ReplyVO vo);
	//댓글삭제
  	public int replyDelete(int renum);
	//조회수 증가
  	public int updateHit(int bonum);
  	
}
