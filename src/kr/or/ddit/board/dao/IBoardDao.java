package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	//전체리스트
	public List<BoardVO> selectAll() throws SQLException;
	
	//페이지별 리스트
	public List<BoardVO> selectByPage(Map<String, Integer> map) throws SQLException;
	
	//전체 글 갯수
	public int boardCount() throws SQLException;
	
	//	글쓰기
	
	
	//	글 수정하기 
	//  삭제
	
	//	댓글저장
	//	댓글 수정 
	//  삭제
	//	조회수 증가
}
