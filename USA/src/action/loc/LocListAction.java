package action.loc;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.loc.LocDAO;
import model.loc.LocVO;
import model.loc.RevDAO;

public class LocListAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loc_regname = request.getParameter("loc_regname");
		request.setAttribute("loc_regname", loc_regname);
		
		LocDAO locdao = LocDAO.getInstance();
		
		ArrayList<LocVO> loc_nameFileList = locdao.getLocFileName(loc_regname);
		request.setAttribute("loc_nameFileList", loc_nameFileList);
		
		RevDAO revdao = RevDAO.getInstance();
		ArrayList<Double> loc_scoreList =  revdao.getAllLocScore();
		request.setAttribute("loc_scoreList", loc_scoreList);
		
		return "/view/loc/locList.jsp";
	}
	
}
