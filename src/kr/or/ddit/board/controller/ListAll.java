package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		//전체리스트 
		
		//1.클라이언트  요청시 전송되는 데이터 받기
		//2.service객체 얻기
		IBoardService service = BoardServiceImpl.getService();
		
		//3.service메소드 호출
		List<BoardVO> list = service.selectAll();
		//4.결과값을 request scope에 저장
		request.setAttribute("listvalue", list);
		
		//5.jsp로
		RequestDispatcher disp = request.getRequestDispatcher("board/listAll.jsp");
		disp.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이지별 리스트
		//1.클라이언트 요청시 전송되는 데이터 받기
		int page = Integer.parseInt(request.getParameter("page"));
		
		//2.service 객체 얻어오기
		IBoardService service = BoardServiceImpl.getService();
		
		//3.service메소드 호출 
		//게시판 글의 총 갯수
		int totalcount = service.boardCount();
		
		//한페이지당 출력 할 글 갯수 
		int perlist = 3;
		int perpage = 2;
		int totalpage = (int)Math.ceil((double)totalcount / perlist);
		
		int startpage = ((page - 1) / perpage * perpage) + 1;
		int endpage = startpage + perpage - 1;
		
		if(endpage > totalpage) endpage = totalpage;
		
		
		int start = (page - 1) * perlist + 1;
		int end = start + perlist -1;
		if(end > totalcount) end  = totalcount;
		
		//각 페이지에 출력될 시작번호와 끝번호
		Map<String, Integer>  map = new HashMap<String,Integer>();
		map.put("start", start);
		map.put("end", end);
		
		
		//페이지에 출력될 글 가져오기
		List<BoardVO> list = service.selectByPage(map);
		
		
		//4.결과값을  requset scope에 저장
		request.setAttribute("listpage", list);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		//5jsp.
		RequestDispatcher disp	= request.getRequestDispatcher("board/listpage.jsp");
		disp.forward(request, response);

	}

}
