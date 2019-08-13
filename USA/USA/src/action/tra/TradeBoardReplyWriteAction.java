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
import model.tra.TradeBoardreplyVO;



public class TradeBoardReplyWriteAction extends HttpServlet {


	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		TradeBoardreplyDAO dao=new TradeBoardreplyDAO();
		TradeBoardreplyVO vo=new  TradeBoardreplyVO();
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
			int    trarepnum=dao.getreplynum();
			//원게시판번호
			int trarepTranum=(int)(long)jsonObject.get("tranum");
			String trarepContent=  (String) jsonObject.get("content");
			String trarepWriter=(String) jsonObject.get("writer");
			int ref= Integer.parseInt((String) jsonObject.get("ref"));
			int trarepWriternum=trarepnum; //부모그룹을 만들어주는데 그것은 자기자신의 고유번호로 지정
			if(ref==0) trarepWriternum=trarepnum;
			String trareprepwriter=null;
			int trarepNumlv=0;
			if(ref==1) {
				trareprepwriter=(String)jsonObject.get("wrtierrepwriter");//누구의 대댓글인지.
				trarepWriternum=(int)(long) jsonObject.get("writernum");//부모작성자번호
				trarepNumlv=(int)(long)jsonObject.get("numlv"); //답글순서
				
			}
//		
			//1댓글 고유번호
			vo.setTrarep_num(trarepnum);
			//2게시판 번호
			vo.setTrarep_tranum(trarepTranum);
			//3내용
			vo.setTrarep_contents(trarepContent);
			//4작성자
			vo.setTrarep_writer(trarepWriter);
			//5부모작성자 번호
			vo.setTrarep_writerrep(trarepWriternum);
			//6.대댓글 유무
			vo.setTrarep_numref(ref);
			//6.1 대댓글이라면 누구의 대댓글인지
			vo.setTrarep_writerrepwriter(trareprepwriter);
			//7.대댓글 순서
			vo.setTrarep_numref_lv(trarepNumlv);
			
			result = dao.insertReply(vo);
			
			
	

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
//		
//		String jsonInfo = totalObject.toJSONString();
		writer.print(totalObject);
//		
//		
//		

	
}
}