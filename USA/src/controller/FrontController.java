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
import action.mem.MemFilenameProAction;
import action.mem.MemIdProAction;
import action.mem.MemIdPwdAction;
import action.mem.MemIdValidateAction;
import action.mem.MemLoginFormAction;
import action.mem.MemLoginProAction;
import action.mem.MemPwdNamePhAction;
import action.mem.MemPwdProAction;
import action.mem.MemUpdateFromAction;
import action.mem.MemUpdatePro;
import action.mem.MemWriteProAction;
import action.mem.MemWriterFormAction;
import action.mem.memNaverAction;
import action.reg.RegMainAction;
import action.reg.RegViewAction;
import action.tra.TradeBoardDeleteAction;
import action.tra.TradeBoardDetailAction;
import action.tra.TradeBoardDownloadAction;
import action.tra.TradeBoardListAction;
import action.tra.TradeBoardModifyFormAction;
import action.tra.TradeBoardModifyProAction;
import action.tra.TradeBoardReplyDeleteAction;
import action.tra.TradeBoardReplyListAction;
import action.tra.TradeBoardReplyModifyAction;
import action.tra.TradeBoardReplyWriteAction;
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
		com = com.substring(com.lastIndexOf("/")+1);
		String nextPage = "";
		
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
		} else if(com.equals("money.do")) {
	        MoneyAction.execute(request, response);
	        return;
		} else if(com.equals("index.do")) {
			response.sendRedirect("index.jsp");
			
		// 회원가입 폼
		} else if(com.equals("memWriterForm.do")) {
			nextPage = new MemWriterFormAction().execute(request, response);
			
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
			nextPage = "/view/mem/memId.jsp";
			
		// 아이디 찾기 실행
		} else if(com.equals("memIdPro.do")) {
			nextPage = new MemIdProAction().execute(request, response);
			
		// 비밀번호 찾기 폼(아이디)
		} else if(com.equals("memPwdForm.do")) {
			nextPage = "/view/mem/memPwd.jsp";
		
		// 비밀번호 찾기 폼(이름, 주소)
		} else if(com.equals("memPwdForm2.do")) {
			nextPage = "/view/mem/memPwd2.jsp";
		
		// 비밀번호 변경 폼(아이디, 이름, 주소)
		} else if(com.equals("memPwdForm3.do")) {
			nextPage = new MemPwdNamePhAction().execute(request, response);
		
		// 비밀번호 변경 폼(아이디, 비밀번호)
		} else if(com.equals("memIdPwdForm.do")) {
			nextPage = new MemIdPwdAction().execute(request, response);
		
		// 비밀번호 재설정 폼
		} else if(com.equals("memPwd3Form.do")) {
			nextPage = "/view/mem/memPwd3.jsp";
			
		// 회원사진 변경 실행
		} else if(com.equals("memFilenamePro.do")) {
			nextPage = new MemFilenameProAction().execute(request, response);
	
		// 비밀번호 변경 실행
		} else if(com.equals("memPwdPro.do")) {
			nextPage = new MemPwdProAction().execute(request, response);
			
		// 회원탈퇴
		} else if(com.equals("memDelPro.do")) {
			nextPage = new MemDelProAction().execute(request, response);
			
		// 로그인 유효성 검사
		} else if(com.equals("memIdValidate.do")) {
			new MemIdValidateAction().execute(request, response);
			return;
		// 환율 	
		} else if(com.equals("money.do")) {
			MoneyAction.execute(request, response);
			return;
		
		// 네이버 로그인
		} else if(com.equals("naver.do")) {
			nextPage = new memNaverAction().execute(request, response);
		
		
		//리스트
		} else if(com.equals("list.do")) {
		nextPage=new TradeBoardListAction().execute(request, response);
		System.out.println("list");
		
		//글쓰기 누르면 글쓰기폼이 나옴
		}else if (com.equals("writeForm.do")) {
			nextPage= "/view/tra/WriteForm.jsp";
		
		//글쓰기 완료 누르면 실행
		}else if(com.equals("TradeBoardWriteAction.do")) {
			nextPage=new TradeBoardWriteAction().execute(request, response);
			System.out.println("여기는 글쓰기 완료 실행"+nextPage);
		//글 자세히 보기
		}else if(com.equals("content.do")) {
			System.out.println("여기는 컨텐츠 컨트롤러");
			nextPage=new TradeBoardDetailAction().execute(request, response);
			
		//다운로드 처리
		}else if(com.equals("FileDownload.do")) {
			
			new TradeBoardDownloadAction().execute(request, response);
			
			
			System.out.println("들르나요?");
		
			
			//글쓰기수정 눌렀을떄
		}else if(com.equals("TradeBoardModifyFormAction.do")) {
			nextPage=new TradeBoardModifyFormAction().execute(request, response);
			
			
			//글쓰기수정 완료할떄
		}else if(com.equals("TradeBoardModifyProAction.do")) {
			System.out.println("수정완료 돌기는 하나요?");
			nextPage=new TradeBoardModifyProAction().execute(request, response);
			
			//삭제 눌렀을떄
		}else if(com.equals("TradeBoardDeleteAction.do")) {
			nextPage=new TradeBoardDeleteAction().execute(request, response);

			
			//댓글 글쓰기 눌렀을떄
		}else if(com.equals("TradeBoardReplyWriteAction.do")) {
			new TradeBoardReplyWriteAction().execute(request, response);
			return ;
			
			
		}else if(com.equals("TradeBoardReplyModifyAction.do")) {
			new TradeBoardReplyModifyAction().execute(request, response);
			return ;
		}else if(com.equals("TradeBoardReplyDeleteAction.do")) {
			new TradeBoardReplyDeleteAction().execute(request, response);
			return ;
			
		}else if(com.equals("TradeBoardReplyListAction.do")) {
			new TradeBoardReplyListAction().execute(request, response);
			return ;
		}
		else if(com.equals("main.do")){
			 nextPage=new MainAction().execute(request, response);
		}
		
		System.out.println("com: "+com);
		System.out.println("nextPage: "+nextPage);
		if(nextPage!=null&&nextPage!="") {
	         System.out.println("여기로 돌기는 하나요?");
	         RequestDispatcher dis = request.getRequestDispatcher(nextPage);
	         if(!nextPage.equals("/index.jsp")) request.setAttribute("contents", nextPage);
	         dis.forward(request, response);
		
		} 
		

		
		
	}

}
