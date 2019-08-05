package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Command;
import model.mem.MemDAO;

public class MemDelProAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mem_id = (String)session.getAttribute("member");
		int check = MemDAO.getInstance().delete(mem_id);
		if(check > 0) {
			session.invalidate();
			return "/index.jsp";
		} else {
			return "";
		}
	}

}
