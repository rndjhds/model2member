package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;


public class Idcheck implements Action {

	@Override	// 비동기식은 처리방식이 다르다.
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Idcheck");
		
		request.setCharacterEncoding("utf-8");
	
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		System.out.println("id:"+id);
		
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.idcheck(id);	// ID중복검사 
		System.out.println("result:"+result);	// 1 : 중복 ID
												// -1 : 사용가능한 ID
		// 웹브라우저에 출력되는 값이 callback 함수로 리턴된다. 
		out.println(result); // 콜백함수로 리턴
		
		return null; // 콜백함수의 data가 int형이므로 null을 리턴
	}
	
}
