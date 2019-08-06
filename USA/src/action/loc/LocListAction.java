package action.loc;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.loc.LocDAO;
import model.loc.LocVO;

public class LocListAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loc_regname = request.getParameter("loc_regname");
		
		LocDAO dao = LocDAO.getInstance();
		
		ArrayList<LocVO> loc_nameFileList = dao.getLocName(loc_regname);
		request.setAttribute("loc_regname", loc_regname);
		request.setAttribute("loc_nameFileList", loc_nameFileList);
		
		return "/view/loc/locList.jsp";
	}
	
}
