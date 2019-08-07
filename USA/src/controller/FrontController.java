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
		
		System.out.println("com: "+com);
		
		String nextPage = "";

		// 각자 알아서 매핑을 이용하여 사용하세요. 
		if(com == null && com.length() <= 0) {
			// 예시입니다.
			nextPage = "/index.jsp";
		
		} 
		if(com.equals("view/reg/regMain.do")) {
			nextPage = new RegMainAction().execute(request, response);
		} else if(com.equals("view/reg/regView.do")) {
			nextPage = new RegViewAction().execute(request, response);
		} else if(com.equals("view/reg/locList.do")) {
			nextPage = new LocListAction().execute(request, response);
		} else if(com.equals("view/reg/locView.do")) {
			nextPage = new LocViewAction().execute(request, response);
		} else if(com.equals("view/reg/locWritePro.do")) {
			nextPage = new LocWriteProAction().execute(request, response);
		} else if(com.equals("view/reg/locModifyPro.do")) {
			nextPage = new LocModifyProAction().execute(request, response);
		} else if(com.equals("view/reg/locDeletePro.do")) {
			nextPage = new LocDeleteProAction().execute(request, response);
		}
		System.out.println("nextPage: "+nextPage);
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
		
	}

}
