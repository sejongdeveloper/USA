package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;

public class MemLoginFormAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("mem_id")) {
					request.setAttribute("cookie_mem_id", cookie.getValue());
					break;
				}
			}
			
		}
		return "/view/mem/memLogin.jsp";
	}

}
