package action.tra;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.tra.TradeBoardDAO;
import model.tra.TradeBoardVO;
public class TradeBoardDetailAction  implements Command{
	

	
	public String execute(HttpServletRequest request,
				 HttpServletResponse response) { 
			 
			try {
				request.setCharacterEncoding("UTF-8");
				TradeBoardDAO dao= new TradeBoardDAO();
				TradeBoardVO vo=new TradeBoardVO();
				
				//지울것
//				int num2=(int)request.getAttribute("num");
				
				int num=Integer.parseInt(request.getParameter("num"));
			
				System.out.println("오나요"+num);
				
				
				dao.updateCount(num);
				vo=dao.getDetail(num);
				if(vo==null){
					System.out.println("상세보기 실패");
					return "/view/tra/list.jsp";
				}
				System.out.println("상세보기 성공");
				request.setAttribute("vo", vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
	   		
		   	
	   		return "/view/tra/Content.jsp";

		 }
	}

