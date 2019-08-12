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

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_name = request.getParameter("mem_name");
		String mem_addr = request.getParameter("mem_addr");
		ArrayList<MemVO> list = MemDAO.getInstance().id(mem_name, mem_addr);
		if(list.size() > 0) {
			request.setAttribute("mem_list", list);
			return "/view/mem/memIdPro.jsp";
		} else {
			response.sendRedirect(request.getHeader("referer") + "?err=true");
			return "";
		}
	}

}
