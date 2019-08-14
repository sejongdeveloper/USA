package action.loc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.loc.LocDAO;
import model.loc.LocVO;

public class LocListModifyForm implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String loc_name = request.getParameter("loc_name");
		// 입력된 관광지 정보 가져오기
		LocVO locListVO = LocDAO.getInstance().getLocModiContents(loc_name);
		request.setAttribute("locListVO", locListVO);
		
		return "/view/loc/locListModifyForm.jsp";
	}

}
