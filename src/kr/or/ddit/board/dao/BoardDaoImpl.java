package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.ibatis.config.BuildSqlMapClient;

/*
 * Mapper를 실행하기 위한 SqlMapClient 객체가 필요 
 * Service에서 사용할dao 객체 생성하고 리턴
 * 
 */

public class BoardDaoImpl implements IBoardDao{

	private SqlMapClient client;
	private static IBoardDao dao;
	
	private BoardDaoImpl() {
		client =BuildSqlMapClient.getSqlMapClient();
	}
	
	public static IBoardDao getDao(){
		if(dao ==null) dao = new BoardDaoImpl();
		
		return dao;
	}
	
	
	@Override
	public List<BoardVO> selectAll() throws SQLException {
		List<BoardVO> list = null;
		
		
			list = client.queryForList("board.selectAll");
	
		
		
		return list;
	}

	@Override
	public List<BoardVO> selectByPage(Map<String, Integer> map) throws SQLException {
		
		return client.queryForList("board.selectByPage", map);
	}

	@Override
	public int boardCount() throws SQLException {
		return (Integer) client.queryForObject("board.boardCount");
	}

	
	
}
