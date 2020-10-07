package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;







import com.ibatis.sqlmap.client.SqlMapClient;






import kr.or.ddit.board.service.IBoardService;
/*
 * Mapper를 실행하기 위한 SqlMapClient 객체가 필요 - BuildSqlMapClient에서 얻어오기
 * service에서 사용할 dao객체를 생성하고 리턴 
 */
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;
import kr.or.ddit.ibatis.config.BuildSqlMapClient;

public class BoardDaoImpl implements IBoardDao {

	//객체 선언 
	private SqlMapClient client;
	private static IBoardDao dao;
	
	private BoardDaoImpl(){
		client = BuildSqlMapClient.getSqlMapClient();
	}
	
	public static IBoardDao getDao(){
		if(dao == null) dao = new BoardDaoImpl();
		
		return dao;
	}
	
	@Override
	public List<BoardVO> selectAll() {
		List<BoardVO> list = null;
		
		try {
			list = client.queryForList("board.selectAll");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BoardVO> selectByPage(Map<String, Integer> map) throws SQLException{
		// TODO Auto-generated method stub
		//return client.queryForList("BoardVO.selectByPage",map);
		//return null;
		return client.queryForList("board.selectByPage",map);
	}

	@Override
	public int boardCount() throws SQLException{
		// TODO Auto-generated method stub
		return (Integer)client.queryForObject("board.boardCount");
	}

	@Override
	public int replySave(ReplyVO vo) throws SQLException{
		// TODO Auto-generated method stub
		return (Integer)client.insert("reply.replySave",vo);
	}
	
	
	@Override
	public List<ReplyVO> replyList(int bonum) throws SQLException {
		// TODO Auto-generated method stub
		return client.queryForList("reply.resultlist",bonum);
	}

	@Override
	public int replyDelete(int renum) throws SQLException {
		// TODO Auto-generated method stub
		return client.delete("reply.replyDelete",renum);
	}

	@Override
	public int replyUpdate(ReplyVO vo) throws SQLException {
		
		return client.update("reply.replyUpdate",vo);
	}

	@Override
	public int updateHit(int bonum) throws SQLException {

		return client.update("board.updateHit",bonum);
	}

	@Override
	public int insertBoard(BoardVO vo) throws SQLException {
		return (int) client.insert("board.insertBoard", vo);
	}

	@Override
	public int updateBoard(BoardVO vo) throws SQLException {
		return client.update("board.updateBoard", vo);
	}

	@Override
	public int deleteBoard(int bonum) throws SQLException {
		return client.delete("board.deleteBoard", bonum);
	}



}
