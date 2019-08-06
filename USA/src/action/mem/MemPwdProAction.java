package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.mem.MemDAO;

public class MemPwdProAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = request.getParameter("mem_id");
		String mem_name = request.getParameter("mem_name");
		String mem_ph = request.getParameter("mem_ph");
		String mem_pwd = MemDAO.getInstance().pwd(mem_id, mem_name, mem_ph);
		
		if(mem_pwd != null) {
			request.setAttribute("mem_pwd", mem_pwd);
			return "/view/mem/memPwd.jsp";
		} else {
			return "";
		}
	}

}
