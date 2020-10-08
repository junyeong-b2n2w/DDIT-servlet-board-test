package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

/**
 * Servlet implementation class BoardUpdate
 */
@WebServlet("/update.do")
public class BoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		BoardVO vo = new BoardVO();
		
		vo.setSeq(Integer.parseInt(request.getParameter("seq")));
		vo.setWriter(request.getParameter("writer"));
		vo.setSubject(request.getParameter("subject"));
		vo.setPassword(request.getParameter("password"));
		vo.setContent(request.getParameter("content"));
		vo.setMail(request.getParameter("mail"));
		vo.setWip(request.getRemoteAddr());
		
		IBoardService service = BoardServiceImpl.getService();
		
		int cnt = service.updateBoard(vo);
		
		request.setAttribute("result", cnt );
		
		request.getRequestDispatcher("board/reply.jsp").forward(request, response);
		
	}

}
