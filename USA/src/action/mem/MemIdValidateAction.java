package action.mem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.mem.MemDAO;

public class MemIdValidateAction {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("유효성 검사 스타트");
		String mem_id = request.getParameter("idInfo");
		boolean isId = MemDAO.getInstance().idValidate(mem_id);
		
		JSONObject idInfo = new JSONObject();
		idInfo.put("isId", isId);
		String info = idInfo.toString();
		
		PrintWriter out = response.getWriter();
		out.print(info);
	}

}
