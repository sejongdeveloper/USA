package action.tra;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.tra.TradeBoardDAO;
import model.tra.TradeBoardVO;

public class TradeBoardListAction implements Command {

	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		
		
//		ActionForward forward = new ActionForward();
	       
		  int pageSize=2;
		  

		  //시작페이지 spage와 page값이 널이 아니면 page값을 시작페이지로 지정.
		  int spage = 1;
	        String page = request.getParameter("page");
	        
	        if(page != null)
	            spage = Integer.parseInt(page);
	        
	        
	        
	        //검색 조건 opt 검색내용 condition
	        String opt = request.getParameter("opt");
	        
	        String condition=request.getParameter("condition");
	        
	        
	        //한글 검색시 오류가 떠서 어쩔수없이 추가함. server.xml에서 urlencoding etc-kr로 바꿀것
	        if(condition!=null) {
	        	
	        	condition=URLDecoder.decode(request.getParameter("condition"),"UTF-8");
	        }
	        
	       System.out.println(condition);
	        
	        
	        
	        
//	       listOpt에 검색조건 검색 내용 시작페이지 start를 저장 
	       HashMap<String, Object> listOpt = new HashMap<String, Object>();
	        listOpt.put("opt", opt);
	        listOpt.put("condition", condition);
	        listOpt.put("start", spage*5-4);
	        
	        TradeBoardDAO dao = TradeBoardDAO.getInstance();
	        int listCount = dao.getBoardListCount(listOpt);
	        ArrayList<TradeBoardVO> list =  dao.getBoardList(listOpt);
	        

	        
	        int pageBlock=5;
	        int pageCount = listCount / pageSize + ( listCount % pageSize == 0 ? 0 : 1 );
	       
	        
	        int startPage = (int)((TradeBoardCurrentPage-1)/pageBlock) * pageBlock + 1;
	        int endPage = startPage +pageBlock- 1;
	        
	        //보여지는 페이지가 마지막 페이지일경우 마지막페이지는 maxpage이다.
	        if(endPage > maxPage)    endPage = maxPage;
	        
	        
	        
	      
	      
			
	        
	        
	        
	        
	        
	        
	        //보여줄 게시글 수를 정함
	        request.setAttribute("pageSize", pageSize);
	        
	        
	        //페이지 숫자에대한 설정
	        request.setAttribute("spage", spage);
	        request.setAttribute("maxPage", maxPage);
	        request.setAttribute("startPage", startPage);
	        request.setAttribute("endPage", endPage);
	        

	        //총 게시물수,게시물내용을 담고있음.
	        request.setAttribute("listCount", listCount);
	        request.setAttribute("list", list);
	        

	        //검색시 검색조건을 유지하기위해 설정함.
	        if(opt!=null) {
	        request.setAttribute("opt", opt);
	        request.setAttribute("condition", condition);
	       }
	        
	      
	        
	        
	       
	    }


}



