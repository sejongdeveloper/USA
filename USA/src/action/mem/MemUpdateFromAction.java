package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.mem.MemDAO;
import model.mem.MemVO;

public class MemUpdateFromAction implements Command {

	// 업데이트 폼
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = (String)request.getSession().getAttribute("member");
		// 아이디를 이용하여 해당 레코드를 조회
		MemVO vo = MemDAO.getInstance().update(mem_id);
		if(vo != null) {
			request.setAttribute("vo", vo);
			return "/view/mem/memUpdate.jsp";
		} else {
			return "";
		}
	}

}
