package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;
/*
 * dao 객체가 필요 daoImpl 클래스에서 얻어오기
 * controller에서 사용할 service객체 생성, 리턴
 */
public class BoardServiceImpl implements IBoardService{
	//객체 변수 선언
	
	private IBoardDao dao;
	private static IBoardService service;
	
	private BoardServiceImpl(){
		dao = BoardDaoImpl.getDao();
	}
	
	public static IBoardService getService(){
		if(service == null) service = new BoardServiceImpl();
		
		return service;
	}
	@Override
	public List<BoardVO> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public List<BoardVO> selectByPage(Map<String, Integer> map){
		List<BoardVO> list = null;
		
		try {
			list = dao.selectByPage(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int boardCount() {
	int count = 0;
		try {
			count = dao.boardCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int replySave(ReplyVO vo) {
		int renum = 0;
		try {
			renum = dao.replySave(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return renum;
	}

	@Override
	public List<ReplyVO> replyList(int bonum) {
		List<ReplyVO> list =null;
		
		try {
			list = dao.replyList(bonum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int replyDelete(int renum) {
		int cnt = 0;
		try {
			cnt = dao.replyDelete(renum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int replyUpdate(ReplyVO vo) {
		int cnt = 0;
		
		try {
			cnt =dao.replyUpdate(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		return cnt;
	}

	@Override
	public int updateHit(int bonum) {
		
		int cnt = 0;
		
		try {
			cnt = dao.updateHit(bonum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return cnt;
	}

	@Override
	public int insertBoard(BoardVO vo) {
		int cnt = 0;
		
		try {
			cnt = dao.insertBoard(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO vo) {
		int cnt = 0;
		
		try {
			cnt = dao.updateBoard(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return cnt;
	}

	@Override
	public int deleteBoard(int bonum) {
		int cnt = 0;
		
		try {
			cnt = dao.deleteBoard(bonum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return cnt;
	}

}
