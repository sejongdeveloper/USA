package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.mem.MemDAO;

public class MemIdValidateAction implements Command{
	
	// 로그인 유효성 검사
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String mem_id = request.getParameter("idInfo"); 
		  boolean isId = MemDAO.getInstance().idValidate(mem_id);
		  response.getWriter().print("{\"result\":" + isId + "}");
		  return null;
	}

}
