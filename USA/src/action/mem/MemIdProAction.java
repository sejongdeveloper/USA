package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.mem.MemDAO;

public class MemIdProAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_name = request.getParameter("mem_name");
		String mem_addr = request.getParameter("mem_addr");
		String mem_id = MemDAO.getInstance().id(mem_name, mem_addr);
		if(mem_id != null) {
			request.setAttribute("mem_id", mem_id);
			return "/view/mem/memId.jsp";
		} else {
			return "";
		}
	}

}
