package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ListAll
 */
@WebServlet("/List.do")
public class ListAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//전체리스트 가져오기
		
		//1. 클라이언트 요청시에 전송되는 데이터
		
		//2. 서비스 객체 얻기
		IBoardService service = BoardServiceImpl.getService();
		//3. 서비스 메소드 호출
		List<BoardVO> list = service.selectAll();
		//4. 결과값을 request scope에 저장
		request.setAttribute("listvalue",list);
		
		//5. jsp로
		
		RequestDispatcher disp = request.getRequestDispatcher("board/listAll.jsp");
		disp.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이지별 리스트
	}

}
