package action.tra;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.tra.TradeBoardDAO;
import model.tra.TradeBoardVO;

public class TradeBoardModifyFormAction implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String num = request.getParameter("num");
		int boardNum = Integer.parseInt(num);

		TradeBoardDAO dao = TradeBoardDAO.getInstance();
		TradeBoardVO vo;
		try {
			vo = dao.getDetail(boardNum);
			request.setAttribute("vo", vo);
			request.setAttribute("pageNum", boardNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return "/view/tra/Modify.jsp";
	}

}
