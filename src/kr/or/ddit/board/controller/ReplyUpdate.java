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
import kr.or.ddit.board.vo.ReplyVO;

/**
 * Servlet implementation class Replyupdate
 */
@WebServlet("/ReplyUpdate")
public class ReplyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//1. 요청시 데이터
		int renum = Integer.parseInt(request.getParameter("renum"));
		String cont = request.getParameter("cont");
		
		ReplyVO vo = new ReplyVO();
		
		vo.setRenum(renum);
		vo.setCont(cont);
		
		//2. service 객체 얻기
		IBoardService service = BoardServiceImpl.getService();
		//3. 메소드호출
		int cnt = service.replyUpdate(vo);
		//4. request scope 에 ㅓ저장
		request.setAttribute("result", cnt);
		
		//5. jsp로
		request.getRequestDispatcher("board/reply.jsp").forward(request, response);
	}

}
