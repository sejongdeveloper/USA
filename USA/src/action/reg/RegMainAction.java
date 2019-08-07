package action.reg;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.reg.RegDAO;

public class RegMainAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegDAO dao = RegDAO.getInstance();
		String reg_name = request.getParameter("reg_name");
		ArrayList<String> reg_filenameList = dao.getRegFileName(reg_name);
		
		request.setAttribute("reg_filenameList", reg_filenameList);
		request.setAttribute("reg_name", reg_name);
		
		return "/view/reg/regMain.jsp";
	}

}
