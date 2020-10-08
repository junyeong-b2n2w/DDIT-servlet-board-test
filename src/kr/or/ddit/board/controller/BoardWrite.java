package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

/**
 * Servlet implementation class BoardWrite
 */
@WebServlet("/writer.do")
public class BoardWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		BoardVO vo = new BoardVO();
		
		vo.setSubject(request.getParameter("subject"));
		vo.setWriter(request.getParameter("writer"));
		vo.setMail(request.getParameter("mail"));
		vo.setContent(request.getParameter("content"));
		vo.setPassword(request.getParameter("password"));
		vo.setWip(request.getRemoteAddr());
		
		IBoardService service = BoardServiceImpl.getService();
		
		int cnt = service.insertBoard(vo);
		
		request.setAttribute("result", cnt);
		
		request.getRequestDispatcher("board/reply.jsp").forward(request, response);
		
		
		
	}

}
