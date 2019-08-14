package action.mem;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.mem.MemDAO;
import model.mem.MemVO;

public class MemIdProAction implements Command {
	
	// 아이디 찾기 실행
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_name = request.getParameter("mem_name");
		String mem_addr = request.getParameter("mem_addr");
		ArrayList<MemVO> list = MemDAO.getInstance().id(mem_name, mem_addr);
		if(list.size() > 0) {
			request.setAttribute("mem_list", list);
			return "/view/mem/memIdPro.jsp";
		} else {
			// request.getHeader("referer")란 현재 요청했던 주소값 얻는 방식
			response.sendRedirect(request.getHeader("referer") + "?err=true");
			return "";
		}
	}

}
