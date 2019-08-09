package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.loc.LocDeleteProAction;
import action.loc.LocListAction;
import action.loc.LocModifyProAction;
import action.loc.LocViewAction;
import action.loc.LocWriteProAction;
import action.main.MainAction;
import action.main.MoneyAction;
import action.mem.MemDelProAction;
import action.mem.MemIdProAction;
import action.mem.MemIdValidateAction;
import action.mem.MemLoginFormAction;
import action.mem.MemLoginProAction;
import action.mem.MemPwdProAction;
import action.mem.MemUpdateFromAction;
import action.mem.MemUpdatePro;
import action.mem.MemWriteProAction;
import action.reg.RegMainAction;
import action.reg.RegViewAction;

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
		com = com.substring(com.lastIndexOf("/")+1);
		String nextPage = "";

		// 각자 알아서 매핑을 이용하여 사용하세요. 
		if(com == null && com.length() <= 0) {
			// 예시입니다.
			nextPage = "/index.jsp";
		
		}
		if(com.equals("regMap.do")) {
			nextPage = "/view/reg/regMap.jsp";
		} else if(com.equals("regMain.do")) {
			nextPage = new RegMainAction().execute(request, response);
		} else if(com.equals("regView.do")) {
			nextPage = new RegViewAction().execute(request, response);
		} else if(com.equals("locList.do")) {
			nextPage = new LocListAction().execute(request, response);
		} else if(com.equals("locView.do")) {
			nextPage = new LocViewAction().execute(request, response);
		} else if(com.equals("locWritePro.do")) {
			nextPage = new LocWriteProAction().execute(request, response);
			response.sendRedirect(nextPage);
			return;
		} else if(com.equals("locModifyPro.do")) {
			nextPage = new LocModifyProAction().execute(request, response);
			response.sendRedirect(nextPage);
			return;
		} else if(com.equals("locDeletePro.do")) {
			nextPage = new LocDeleteProAction().execute(request, response);
			response.sendRedirect(nextPage);
			return;
		} else if(com.equals("event.do")) {
			nextPage = "/view/event/event.jsp";
		} else if(com.equals("main.do")) {
			nextPage = new MainAction().execute(request, response);
		} else if(com.equals("money.do")) { // 환율
	        MoneyAction.execute(request, response);
	        return;
		}
		
		System.out.println("com: "+com);
		System.out.println("nextPage: "+nextPage);
		
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
		
	}

}
