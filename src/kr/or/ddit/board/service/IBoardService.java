package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardService {

	//전체리스트
	public List<BoardVO> selectAll();
	
	//페이지별 리스트
	public List<BoardVO> selectByPage(Map<String, Integer> map);
	
	//전체 글 갯수
	public int boardCount();
	
	//	글쓰기
	
	
	//	글 수정하기 
	//  삭제
	
	//	댓글저장
	public int replySave(ReplyVO vo);
	//	댓글 수정 
	//  삭제
	//	조회수 증가
}
