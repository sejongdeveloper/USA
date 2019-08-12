package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.mem.MemDAO;
import model.mem.MemVO;

public class MemUpdateFromAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = (String)request.getSession().getAttribute("member");
		MemVO vo = MemDAO.getInstance().update(mem_id);
		if(vo != null) {
			request.setAttribute("vo", vo);
			return "/view/mem/memUpdate.jsp";
		} else {
			return "";
		}
	}

}
