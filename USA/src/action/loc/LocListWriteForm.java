package action.loc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;

public class LocListWriteForm implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String loc_regname = request.getParameter("loc_regname");
		request.setAttribute("loc_regname", loc_regname);
		
		return "/view/loc/locListWriteForm.jsp";
	}

}
