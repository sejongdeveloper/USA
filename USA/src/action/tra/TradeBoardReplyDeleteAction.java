package action.tra;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.tra.TradeBoardreplyDAO;



public class TradeBoardReplyDeleteAction extends HttpServlet {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8");
		TradeBoardreplyDAO dao=new TradeBoardreplyDAO();
		boolean result=false;
		PrintWriter writer = response.getWriter();
		//받을떄
		
		String jsoninfo=request.getParameter("data");
		JSONParser jsonParser=new JSONParser();
	
		try {
			
				
				JSONObject jsonObject = (JSONObject) jsonParser.parse(jsoninfo);
				//json값 가져올떄 long형으로 가져옴
				/*
				 contet 글 내용
				 writer 작성자
				 ref  답글인지 아닌지  1일떄 부모 작성자번호 누구의 대댓글인지 판단할것.
				 numlv 답글 순서
				 tranum 게시판 번호
				 
				 받아와야할것
				
				  
				 */
				//댓글 고유번호
			int    trarepnum=(int)(long)jsonObject.get("trarepnum");
			result=	dao.replydelete(trarepnum);
			//원게시판번호
				
		
			
			
	

		} catch (ParseException e) {
			e.printStackTrace();
		}
		String msg = null;
		if(!result) {
			msg="댓글 등록에 실패하셨습니다.";
		}
		
// 댓글 성공인지 적는것
		JSONObject totalObject = new JSONObject();
		

		totalObject.put("result",msg);
		writer.print(totalObject);

	
	}
}