package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import action.tra.TradeBoardListAction;

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
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String com = requestURI.substring(contextPath.length() + 1);
		
		Command command = null;
		String nextPage = "";
		System.out.println(com);
		System.out.println(request.getParameter("hwa"));
		// 각자 알아서 매핑을 이용하여 사용하세요. 
		if(com == null && com.length() <= 0) {
			// 예시입니다.
			nextPage = "/index.jsp";
		}else if(com.equals("view/tra/list.do")) {
				new TradeBoardListAction().execute(request, response);
				nextPage="/view/tra/list.jsp";
				
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
	}

}
