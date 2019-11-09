package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.mem.MemDAO;

public class MemUpdatePro implements Command{

	// 업데이트 실행
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = request.getParameter("mem_id"); // 변결항 대상 아이디
		String calc = request.getParameter("colc"); // 변경할 컬럼
		String mem_value = request.getParameter("mem_value"); // 변결할 값
		
		int check = MemDAO.getInstance().update(mem_id, calc, mem_value);
		// xml 방식으로 데이터 전달
		response.getWriter().print("<result>" + check + "</result>");
		
		if(check > 0 && calc.equals("mem_name")) {
			request.getSession().setAttribute("nickname", mem_value);
		}
		
		return null;
	}

}
