package action.loc;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.loc.LocDAO;

public class LocListAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocDAO dao = LocDAO.getInstance();
		
		String loc_regName = request.getParameter("loc_regname");
		HashMap<String, String> loc_nameFileList = dao.getLocName(loc_regName);
		
		request.setAttribute("loc_nameFileList", loc_nameFileList);
		
		return "view/loc/locList.jsp";
	}
	
}
