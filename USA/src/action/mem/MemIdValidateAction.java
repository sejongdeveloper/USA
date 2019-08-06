package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.mem.MemDAO;

public class MemIdValidateAction {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  System.out.println("유효성 검사 스타트"); 
		  String mem_id = request.getParameter("idInfo"); 
		  boolean isId = MemDAO.getInstance().idValidate(mem_id);
		  
		  JSONObject obj = new JSONObject(); 
		  obj.put("result", isId);
		  
		  String info = obj.toJSONString(); //
		  
		  response.getWriter().print(info);
	}

}
