package action.mem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.mem.MemDAO;
import model.tra.TradeBoardreplyDAO;

public class MemEvent {
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("text/html);charset=UTF-8");
		boolean result=false;
		PrintWriter writer = response.getWriter();
		//받을떄
		String jsoninfo=request.getParameter("data");
		
		JSONParser jsonParser=new JSONParser();

		try {
			JSONObject jsonObject=(JSONObject) jsonParser.parse(jsoninfo);
			
		String member=(String) jsonObject.get("session");
		MemDAO dao=MemDAO.getInstance();
		result=dao.lostPoint(member);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String msg = null;
		if(!result) {
			msg="댓글 등록에 실패하셨습니다.";
		}
		
// 댓글 성공인지 적는것
		JSONObject totalObject =new JSONObject();
		

		totalObject.put("result",msg);
		writer.print(totalObject);

	
	}
}
