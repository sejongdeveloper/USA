package action.tra;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.tra.TradeBoardDAO;
import model.tra.TradeBoardVO;
import model.tra.TradeBoardreplyDAO;
import model.tra.TradeBoardreplyVO;
public class TradeBoardDetailAction  implements Command{
	

	
	public String execute(HttpServletRequest request,
				 HttpServletResponse response) { 
			 
			try {
				request.setCharacterEncoding("UTF-8");
				//게시판 내용에 대한 정보
				TradeBoardDAO dao= new TradeBoardDAO();
				TradeBoardVO vo=new TradeBoardVO();
				
				TradeBoardreplyDAO dao2=new TradeBoardreplyDAO();
				ArrayList<TradeBoardreplyVO> replylist=new ArrayList<TradeBoardreplyVO>();
				
				int num=Integer.parseInt(request.getParameter("num"));
			
				
				
				dao.updateCount(num);
				vo=dao.getDetail(num);
				replylist=dao2.getreplylist(num);

				if(vo==null){
					return "/view/tra/list.jsp";
				}

				request.setAttribute("vo", vo);
				request.setAttribute("replylist", replylist);
			} catch (Exception e) {
				e.printStackTrace();
			}
	   		
		   	
	   		return "/view/tra/content.jsp";

		 }
	}

