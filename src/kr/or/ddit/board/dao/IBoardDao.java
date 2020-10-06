package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardDao {
	//전체리스트
		public List<BoardVO> selectAll();
		//페이지별 리스트 -
	  	public List<BoardVO> selectByPage(Map<String, Integer> map) throws SQLException;

	  	//전체글 개수
	  	public int boardCount() throws SQLException;
		//글쓰기
		//글 수정하기  
		//글 삭제

		//댓글저장
		public int replySave(ReplyVO vo) throws SQLException;
		
		//댓글리스트
		public List<ReplyVO> replyList(int bonum) throws SQLException;
		//댓글수정 
		public int replyUpdate(ReplyVO vo) throws SQLException;
		//댓글삭제
	 	public int replyDelete(int renum) throws SQLException;
		//조회수 증가
	 	public int updateHit(int bonum) throws SQLException;
}
