package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.mem.MemDelProAction;
import action.mem.MemIdProAction;
import action.mem.MemIdValidateAction;
import action.mem.MemLoginFormAction;
import action.mem.MemLoginProAction;
import action.mem.MemPwdProAction;
import action.mem.MemUpdateFromAction;
import action.mem.MemUpdatePro;
import action.mem.MemWriteProAction;

@WebServlet("*.do")
public class FrontController extends HttpServlet implements Process{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러에 오신걸 환영합니다 ^^");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String com = requestURI.substring(contextPath.length() + 1);
		
		String nextPage = "";

		// 각자 알아서 매핑을 이용하여 사용하세요. 
		if(com == null && com.length() <= 0) {
			// 예시입니다.
			nextPage = "/index.jsp";
			
		// 회원가입 폼
		} else if(com.equals("memWriterForm.do")) {
			nextPage = "/view/mem/memWrite.jsp";
			
		// 회원가입 실행	
		} else if(com.equals("memWritePro.do")) {
			nextPage = new MemWriteProAction().execute(request, response);
			
		// 로그인 폼
		} else if(com.equals("memLoginForm.do")) {
			nextPage = new MemLoginFormAction().execute(request, response);
			
		// 로그인 실행
		} else if(com.equals("memLoginPro.do")) {
			nextPage = new MemLoginProAction().execute(request, response);
		
		// 로그아웃
		} else if(com.equals("memLogout.do")) {
			System.out.println("로그아웃");
			request.getSession().invalidate();
			nextPage = "/index.jsp";
			
		// 업데이트 폼
		} else if(com.equals("memUpdateForm.do")) {
			nextPage = new MemUpdateFromAction().execute(request, response);
			
		// 업데이트 실행
		} else if(com.equals("memUpdatePro.do")) {
			nextPage = new MemUpdatePro().execute(request, response);
			
		// 아이디 찾기 폼
		} else if(com.equals("memIdForm.do")) {
			System.out.println("아이디 찾기 폼");
			nextPage = "/view/mem/memId.jsp";
			
		// 아이디 찾기 실행
		} else if(com.equals("memIdPro.do")) {
			nextPage = new MemIdProAction().execute(request, response);
			
		// 비밀번호 찾기 폼
		} else if(com.equals("memPwdForm.do")) {
			nextPage = "/view/mem/memPwd.jsp";
			
		// 비밀번호 찾기 실행
		} else if(com.equals("memPwdPro.do")) {
			nextPage = new MemPwdProAction().execute(request, response);
			
		// 회원탈퇴
		} else if(com.equals("memDelPro.do")) {
			nextPage = new MemDelProAction().execute(request, response);
			
		// 로그인 유효성 검사
		} else if(com.equals("memIdValidate.do")) {
			new MemIdValidateAction().execute(request, response);
		} 
	
		String path = request.getContextPath();
		System.out.println("path:" + path);
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
		 
	}

}
