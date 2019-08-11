package action.mem;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.mem.MemDAO;

public class MemIdPwdAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = (String)request.getSession().getAttribute("member");
		String mem_pwd = request.getParameter("mem_pwd");
		boolean isLogin = MemDAO.getInstance().login(mem_id, mem_pwd);
		
		response.getWriter().print("{\"result\":" + isLogin + "}");
		return null;
	}

}
