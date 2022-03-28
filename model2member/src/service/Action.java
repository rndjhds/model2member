package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// 일관되게  작성하기 위해서 추상메소드를 하나 만들어 준다.

	// 추상 메소드
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
