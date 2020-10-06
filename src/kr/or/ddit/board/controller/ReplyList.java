package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.ReplyVO;

/**
 * Servlet implementation class ReplyList
 */
@WebServlet("/ReplyList" )
public class ReplyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청시 전송되는 값
		int bonum = Integer.parseInt(request.getParameter("bonum"));
		
		//2.service객체 얻기
		IBoardService service = BoardServiceImpl.getService();
		
		List<ReplyVO> list = service.replyList(bonum);
		
		//4.request에 저장
		request.setAttribute("list", list);
		
		//5.jsp
		request.getRequestDispatcher("board/replyList.jsp").forward(request, response);
	}

}
