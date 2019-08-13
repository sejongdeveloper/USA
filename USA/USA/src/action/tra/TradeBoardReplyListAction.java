package action.tra;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.tra.TradeBoardreplyDAO;
import model.tra.TradeBoardreplyVO;



public class TradeBoardReplyListAction extends HttpServlet {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		//받을떄
		String jsoninfo=request.getParameter("data");
		JSONParser jsonParser=new JSONParser();
		int tranum=0;//초기화
		int currentpage=1;
		String pagestatus = "start";
		
		try {
			
				
				JSONObject jsonObject = (JSONObject) jsonParser.parse(jsoninfo);
				
				//json값 가져올떄 long형으로 가져옴
				
				//게시판번호
			 tranum=(int)(long)jsonObject.get("tranum");
			  pagestatus=(String) jsonObject.get("pagestatus");
			 
			 //최근페이지
			currentpage=Integer.parseInt((String) jsonObject.get("currentpage")); 

		} catch (ParseException e) {
			e.printStackTrace();
		}

		
		
		
 

		
		
		
		
		///여기부터 세션체크할려고 적는것
		JSONObject totalObject = new JSONObject();
		JSONArray  replyarray = new JSONArray();
		JSONObject replyinfo = new JSONObject();
		TradeBoardreplyDAO dao=new TradeBoardreplyDAO();
		TradeBoardreplyVO vo=new TradeBoardreplyVO();
		ArrayList<TradeBoardreplyVO> list=new  ArrayList<TradeBoardreplyVO>();
		
		
		
		
		//아래부터는 페이지 변수 필요한것들.
		//게시글 숫자를 구함
		list=dao.getreplylist(tranum);
		
		
		//한번에 몇개의 게시물을 볼것인지
		 int pageSize=5;
		 //게시물 마지막 번호를 구하기 위한 변수
		 int pageSizeref=pageSize-1;
		///아래부터는 페이징처리
		 int pageBlock=3;
    		//전체 몇개인지는 list.size()
			int listcount=list.size();
		 //전체 페이지 크기
	        int pageCount = listcount / pageSize + ( listcount % pageSize == 0 ? 0 : 1 );
		
	        int startPage = (int)((currentpage-1)/pageBlock) * pageBlock + 1;
	        int endPage = startPage +pageBlock- 1;
	        if(pageCount<endPage) endPage=pageCount;
	        
	        if(pagestatus.equals("start")) {
	        	currentpage=1;
	        }else if(pagestatus.equals("insert")) {
	        	currentpage=pageCount;
	        }//move일경우 값 변경이 없으니 
		 
		 
		 //시작 댓글
		 int TradeBoardReplystartnum=currentpage*pageSize-pageSizeref;
		 
		 //한번에 볼 페이지
		 int TradeBoardReplypagenum=5;
		
		 int TradeBoardReplyendnum=TradeBoardReplystartnum+pageSize-1;
		 
		 
		
		//한번에 보여줄 게시글 내용 담기
		 ArrayList<TradeBoardreplyVO> replylist=dao.getreplylist2(tranum, TradeBoardReplystartnum, TradeBoardReplyendnum);
		 //
		 
		 
	        
	        JSONArray jsonpage=new JSONArray();
			JSONObject jsonpageobject=new JSONObject();
			jsonpageobject.put("pagecount",pageCount);
			jsonpageobject.put("startpage",startPage);
			jsonpageobject.put("endpage",endPage);
			jsonpageobject.put("pageblock",pageBlock);
			jsonpageobject.put("currentpage",currentpage);
			jsonpage.add(jsonpageobject);
			totalObject.put("page",jsonpage);
	        
	        

		for(int i=0;i<replylist.size();i++) {
			replyinfo=new JSONObject();
			replyinfo.put("num", replylist.get(i).getTrarep_num());
			replyinfo.put("tranum", replylist.get(i).getTrarep_tranum());
			replyinfo.put("content", replylist.get(i).getTrarep_contents());
			replyinfo.put("writer", replylist.get(i).getTrarep_writer());
			replyinfo.put("trarep_writerrep",replylist.get(i).getTrarep_writerrep());
			replyinfo.put("numref",replylist.get(i).getTrarep_numref());
			replyinfo.put("trarep_numref_lv", replylist.get(i).getTrarep_numref_lv());
			replyinfo.put("alive",replylist.get(i).getTrarep_alive());
			replyinfo.put("trarep_writerrepwriter",replylist.get(i).getTrarep_writerrepwriter());
			replyinfo.put("trarep_date",replylist.get(i).getTrarep_date());
			replyarray.add(replyinfo);
		
	}		
		
		totalObject.put("replyinfo", replyarray);
		totalObject.put("totalreply",list.size());

		

		
		
		String session =(String) request.getSession().getAttribute("member");
		JSONArray jsonsession=new JSONArray();
		JSONObject jsonobject=new JSONObject();
		jsonobject.put("session",session);
		jsonsession.add(jsonobject);
		totalObject.put("session",jsonsession);
		
		String jsonInfo = totalObject.toJSONString();
		writer.print(jsonInfo);
		
		
	
}
}