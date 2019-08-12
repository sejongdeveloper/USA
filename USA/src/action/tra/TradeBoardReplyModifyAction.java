package action.tra;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.tra.TradeBoardreplyDAO;
import model.tra.TradeBoardreplyVO;


public class TradeBoardReplyModifyAction extends HttpServlet {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		TradeBoardreplyDAO dao=new TradeBoardreplyDAO();
		boolean result=false;
		PrintWriter writer = response.getWriter();
		//받을떄
		
		String jsoninfo=request.getParameter("data");
		System.out.println(jsoninfo);
		JSONParser jsonParser=new JSONParser();
	
		try {
			
				
				JSONObject jsonObject = (JSONObject) jsonParser.parse(jsoninfo);
				System.out.println(jsonObject);
				//json값 가져올떄 long형으로 가져옴
				
				
			int    trarepnum=(int)(long)jsonObject.get("trarepnum");
			String trarepcontent=(String) jsonObject.get("content");
			result=	dao.replyupdate(trarepnum, trarepcontent);
			//원게시판번호
				
		
			
			
	

		} catch (ParseException e) {
			e.printStackTrace();
		}
		String msg = null;
		if(!result) {
			msg="수정실패";
		}
		
// 댓글 성공인지 적는것
		JSONObject totalObject = new JSONObject();
		

		totalObject.put("result",msg);
		writer.print(totalObject);

	
	}
}