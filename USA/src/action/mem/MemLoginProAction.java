package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.mem.MemDAO;

public class MemLoginProAction implements Command {

	// 로그인 실행
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");
		String idChk = request.getParameter("idChk");
		
		// 아이디와 비밀번호가 일치하는 레코드가 있는지 판단
		boolean check = MemDAO.getInstance().login(mem_id, mem_pwd);
		
		if(check) {
			// 아이디 저장을 눌렀을 경우
			if(idChk.equals("true")) {
				// 쿠키생성
				Cookie cookie = new Cookie("mem_id", mem_id);
				// 1시간 유효시간
				cookie.setMaxAge(60*60);
				response.addCookie(cookie);
			} else if(idChk.equals("false")){
				// mem_id 이름을 가진 쿠키 삭제
				Cookie[] cookies = request.getCookies();
				if(cookies != null) {
					for(Cookie cookie : cookies) {
						String name = cookie.getName();
						if(name.equals("mem_id")) {
							// 해당쿠키 유효시간 0초
							cookie.setMaxAge(0);
							response.addCookie(cookie);
							break;
						}
					} // for end
				} // if(cookies) end
			}
			String nickname = MemDAO.getInstance().nickname(mem_id);
			request.getSession().setAttribute("nickname", nickname);
			request.getSession().setAttribute("member", mem_id);
			
			return "/index.jsp";
		} else {
			// request.getHeader("referer")란 요청한 주소값
			response.sendRedirect(request.getHeader("referer") + "?outmsg=true");
			return null;
		}
		
	}

}
