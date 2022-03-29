package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.ActionForward;
import service.Delete;
import service.Idcheck;
import service.Login;
import service.MemberInsert;
import service.Update;
import service.UpdateMember;

@WebServlet("*.do")	// do 확장자로 요청하는 요청을 받는다는 의미
					// 요청된 이름이 어떤이름이더라도 확장자가 do이면 다 받는다.
					// controller는 한개만 생성하는게 기본적이기 떄문이다.
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// doGet(), doPost() 메소드에서 공통적인 작업을 처리하는 메소드
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()); // 인덱스번호 부터 끝까지 찾아서 출력	
		
		System.out.println("requestURI:"+requestURI);	// /model2member/MemberInsert.do
		System.out.println("contextPath:"+contextPath);	// /model2member
		System.out.println("command:"+command);			// /MemberInsert.do
		
		Action action = null;
		ActionForward forward = null;
		
		// 회원가입
		if(command.equals("/MemberInsert.do")) {
			try {
				action = new MemberInsert();
				forward = action.execute(request, response);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		// ID중복 검사(ajax) : 비동기 처리방식은 조금 다르다(서비스 패키지확인)
		}else if(command.equals("/Idcheck.do")) {
			try {
				action = new Idcheck();
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		// 회원가입 폼
		}else if(command.equals("/MemberForm.do")) {
			// DB에 연결하는 것이 아니라서 Action객체(서비스 클래스) 생성x
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/memberform.jsp");
		
		// 로그인(회원인증)
		}else if(command.equals("/Login.do")) {
			try {
				action = new Login();
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		// 로그인 폼
		}else if(command.equals("/LoginForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/loginform.jsp");
			
		// 로그 아웃	
		}else if(command.equals("/Logout.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/logout.jsp");
			
		// 회원정보 수정폼
		}else if(command.equals("/UpdateMember.do")) {
			try {
				action = new UpdateMember();
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		// 회원정보 수정
		}else if(command.equals("/Update.do")) {
			try {
				action = new Update();
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		// 회원탈퇴 폼
		}else if(command.equals("/DeleteMember.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/deleteform.jsp");
			
		// 회원 탈퇴
		}else if(command.equals("/Delete.do")) {
			try {
				action = new Delete();
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 포워딩 처리
		if(forward != null) {
			if(forward.isRedirect()) {	// redirect 방식으로 포워딩
				response.sendRedirect(forward.getPath());
			}else {	// dispatcher 방식으로 포워딩
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
		
	}	// doProcess() end
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get");
		
		doProcess(request, response);	// doProcess() 메소드 호출 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post");
		
		doProcess(request, response);	// doProcess() 메소드 호출		
	}

}
