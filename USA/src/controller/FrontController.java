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
import action.tra.TradeBoardDetailAction;
import action.tra.TradeBoardDownloadAction;
import action.tra.TradeBoardListAction;
import action.tra.TradeBoardModifyFormAction;
import action.tra.TradeBoardModifyProAction;
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

		System.out.println("여기까지는 도나??");

		String nextPage = "";

		Command command = null;
		System.out.println(com +"이퀄 비교");

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
			System.out.println("여기는 글쓰기 완료 실행"+nextPage);
		//글 자세히 보기
		}else if(com.equals("view/tra/content.do")) {
			System.out.println("여기는 컨텐츠 컨트롤러");
			nextPage=new TradeBoardDetailAction().execute(request, response);
			
		//다운로드 처리
		}else if(com.equals("view/tra/FileDownload.do")) {
			
			new TradeBoardDownloadAction().execute(request, response);
			
			
			System.out.println("들르나요?");
		
			
			//수정 눌렀을떄
		}else if(com.equals("view/tra/TradeBoardModifyFormAction.do")) {
			nextPage=new TradeBoardModifyFormAction().execute(request, response);
			
			
			//수정 완료할떄
		}else if(com.equals("view/tra/TradeBoardModifyProAction.do")) {
			System.out.println("수정완료 돌기는 하나요?");
			nextPage=new TradeBoardModifyProAction().execute(request, response);
			
		}

		if(nextPage!=null&&nextPage!="") {
			RequestDispatcher dis = request.getRequestDispatcher(nextPage);
			System.out.println("여기로 돌기는 하나요?");
			dis.forward(request, response);
			
		}


	}

}
