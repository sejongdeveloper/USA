package action.mem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.mem.MemDAO;

public class MemLoginProAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");
		boolean check = MemDAO.getInstance().login(mem_id, mem_pwd);
		
		if(check) {
			request.getSession().setAttribute("member", mem_id);
		} else {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('아이디 또는 비밀번호 틀림');</script>");
			
		}
	}

}
