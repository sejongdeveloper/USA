package action.tra;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.tra.TradeBoardDAO;

public class TradeBoardDeleteAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int traNum=Integer.parseInt(request.getParameter("num"));
		TradeBoardDAO dao=new TradeBoardDAO();
		boolean flag=dao.deletetraboard(traNum);
		
		
		//나중에 flag 참 거짓에 따라서 list에서 삭제성공 불가 뜨게 구현할것.
		if(flag) {
			return "/view/tra/list.do";
		}
		
		return "/view/tra/list.do";
	}

}
