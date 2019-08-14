package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.mem.MemDAO;

public class MemPwdNamePhAction implements Command {

	// 비밀번호 변경 폼(아이디, 이름, 주소)
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = request.getParameter("mem_id");
		String mem_name = request.getParameter("mem_name");
		String mem_ph = request.getParameter("mem_ph");
		// 아이디, 이름, 전화번호를 이용하여 비밀번호가 존재하는지 판단
		String mem_pwd = MemDAO.getInstance().pwd(mem_id, mem_name, mem_ph);
		
		if(mem_pwd != null) {
			request.setAttribute("mem_pwd", mem_pwd);
			return "/view/mem/memPwd3.jsp";
		} else {
			response.sendRedirect(request.getHeader("referer") + "&err=true");
			return null;
		}
	}

}
