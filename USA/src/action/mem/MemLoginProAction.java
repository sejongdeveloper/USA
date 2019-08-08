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
			Cookie cookie = new Cookie("mem_id", mem_id);
			cookie.setMaxAge(60*60);
			if(idChk.equals("true")) response.addCookie(cookie);
			request.getSession().setAttribute("member", mem_id);
		} else {
			response.sendRedirect("index.jsp?contents=/view/mem/memLogin.jsp&outmsg=true");
			return null;
		}
		
		return "/index.jsp";
	}

}
