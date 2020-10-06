<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	List<BoardVO> list = (List<BoardVO>)request.getAttribute("listpage" );
	int total = (Integer)request.getAttribute("totalpage");
	int sp = (Integer)request.getAttribute("startpage");
	int ep = (Integer)request.getAttribute("endpage");
	
%>
 {
 	"total" : "<%=total %>",
 	"startp" : "<%=sp %>",
 	"endp" : "<%=ep %>",
 	"datas" : [
  




<%
	for(int i = 0; i <list.size(); i++){
		BoardVO vo = list.get(i);
		if(i > 0) out.print(",");
%>
{
	"seq" : "<%=vo.getSeq() %>",
	"writer" : "<%=vo.getWriter()%>",
	"subject" : "<%=vo.getSubject()%>",
	"content" : "<%=vo.getContent()%>",
	"mail"  : "<%=vo.getMail()%>",
	"hit"   : "<%=vo.getHit() %>",
	"wdate" : "<%=vo.getWdate() %>"
}
<%	

}
		
%>
 ] 
}