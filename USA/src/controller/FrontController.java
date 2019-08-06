package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;

import action.mem.MemLoginProAction;
import action.mem.MemWriteProAction;
import action.tra.TradeBoardListAction;
import action.tra.TradeBoardWriteAction;

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

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String com = requestURI.substring(contextPath.length() + 1);
		

		String nextPage = "";

		Command command = null;
		System.out.println(com);

		// 각자 알아서 매핑을 이용하여 사용하세요. 
		if(com == null && com.length() <= 0) {
			// 예시입니다.
			nextPage = "/index.jsp";

			
		// 회원가입 폼
		} else if(com.equals("memWriterForm.do")) {
			nextPage = "/view/mem/memWrite.jsp";
			
		// 회원가입 실행	
		} else if(com.equals("memWritePro.do")) {
			new MemWriteProAction().execute(request, response);
			nextPage = "/index.jsp";
			
		// 로그인 폼
		} else if(com.equals("memLoginForm.do")) {
			nextPage = "/view/mem/memLogin.jsp";
			
		// 로그인 실행
		} else if(com.equals("memWritePro.do")) {
			new MemLoginProAction().execute(request, response);;
			nextPage = "/index.jsp";
			
			//리스트
		}else if(com.equals("view/tra/list.do")) {
		nextPage=new TradeBoardListAction().execute(request, response);
		System.out.println("list");
		
		//글쓰기 누르면 글쓰기폼이 나옴
		}else if (com.equals("view/tra/writeForm.do")) {
			nextPage= "/view/tra/WriteForm.jsp";
		
		//글쓰기 완료 누르면 실행
		}else if(com.equals("view/tra/TradeBoardWriteAction.do")) {
			
			
			nextPage=new TradeBoardWriteAction().execute(request, response);
		}

		
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
		 


	}

}
