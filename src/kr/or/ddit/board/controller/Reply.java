package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.ReplyVO;


@WebServlet("/Reply.do")
public class Reply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			//댓글삭제
			//1.요청시 데이터
			int  renum = Integer.parseInt(request.getParameter("renum"));
			
			IBoardService service = BoardServiceImpl.getService();
			
			int cnt = service.replyDelete(renum);
			
			request.setAttribute("result", cnt);
			
			request.getRequestDispatcher("board/reply.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//1.요청시 데이터
		int  bonum = Integer.parseInt(request.getParameter("bonum"));
		String name = request.getParameter("name");
		String cont = request.getParameter("cont");
		
		ReplyVO vo = new ReplyVO();
		vo.setBonum(bonum);
		vo.setName(name);
		vo.setCont(cont);
		
		//2.service객체
		
		IBoardService service = BoardServiceImpl.getService();
		
		//3.service메소드 호출
		int renum = service.replySave(vo);
		
		//4.request scope에 저장
		request.setAttribute("result", renum);
		
		//5.jsp로 forward
		request.getRequestDispatcher("board/reply.jsp").forward(request, response);
	}

}
