package action.mem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.mem.MemDAO;

public class MemIdValidateAction {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("유효성 검사 스타트");
		String mem_id = request.getParameter("idInfo");
		boolean isId = MemDAO.getInstance().idValidate(mem_id);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		JSONObject totaloObject = new JSONObject();
		JSONArray membersArray = new JSONArray();
		JSONObject memberInfo = new JSONObject();
		
		memberInfo.put("name", "박지성");
		memberInfo.put("age", "25");
		membersArray.add(memberInfo);
		
		memberInfo = new JSONObject();
		memberInfo.put("name", "김연아");
		memberInfo.put("age", "21");
		membersArray.add(memberInfo);
		
		totaloObject.put("members", membersArray);
		
		String jsonInfo = totaloObject.toJSONString();
		System.out.println(jsonInfo);
		writer.print(jsonInfo);
	}

}
