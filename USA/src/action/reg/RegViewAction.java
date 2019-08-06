package action.reg;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.reg.RegDAO;
import model.reg.RegVO;

public class RegViewAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegDAO dao = RegDAO.getInstance();
		String reg_name = request.getParameter("reg_name");
		ArrayList<RegVO> reg_list = dao.getRegContents(reg_name);
		
		request.setAttribute("reg_list", reg_list);
		
		return "/view/reg/regView.do";
	}

}
