package action.mem;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.mem.MemDAO;

public class MemLoginProAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");
		String idChk = request.getParameter("idChk");
		boolean check = MemDAO.getInstance().login(mem_id, mem_pwd);
		
		if(check) {
			if(idChk.equals("true")) {
				Cookie cookie = new Cookie("mem_id", mem_id);
				cookie.setMaxAge(60*60);
				response.addCookie(cookie);
			} else if(idChk.equals("false")){
				Cookie[] cookies = request.getCookies();
				if(cookies != null) {
					for(Cookie cookie : cookies) {
						String name = cookie.getName();
						if(name.equals("mem_id")) {
							System.out.println(name + "삭제!!!!");
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
			response.sendRedirect(request.getHeader("referer") + "?outmsg=true");
			return null;
		}
		
	}

}
