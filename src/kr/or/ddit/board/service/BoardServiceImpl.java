package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

/*
 * dao 객체 필요 -* daoilmple클래스에서 얻어오기
 * 
 * controller에서 사용할 서비스 객체 생성, 리턴
 * 
 */

public class BoardServiceImpl implements IBoardService{

	//객체변수 선언
	
	private IBoardDao dao;
	private static IBoardService service;
	
	private BoardServiceImpl(){
		dao = BoardDaoImpl.getDao();
	}
	
	public static IBoardService getService(){
		if (service == null) service= new BoardServiceImpl();
		return service;
	}
	
	
	
	@Override
	public List<BoardVO> selectAll() {
		List<BoardVO> list = null;
		try {
			list = dao.selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BoardVO> selectByPage(Map<String, Integer> map) {
		List<BoardVO> list = null;
		try {
			list=  dao.selectByPage(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int boardCount() {
		int num = 0;
		try {
			num = dao.boardCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return num;
	}

}
