package action.mem;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.mem.MemDAO;

public class MemPwdProAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");
		int check = MemDAO.getInstance().update(mem_id, mem_pwd);
		
		if(check > 0) {
			response.sendRedirect("index.jsp?msg=<script>alert('비밀번호 변경 성공')</script>");
			return null;
		} else {
			response.sendRedirect(request.getHeader("referer") + "&err=" + URLEncoder.encode("회원정보를 정확하게 입력해 주세요", "utf-8"));
			return null;
		}			
	}

}
