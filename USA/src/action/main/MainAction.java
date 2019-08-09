package action.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.loc.LocDAO;
import model.loc.LocVO;
import model.loc.RevDAO;

public class MainAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		LocDAO locdao = LocDAO.getInstance();
		
		ArrayList<LocVO> loc_allNameFileList = locdao.getAllLocFileName();
		request.setAttribute("loc_allNameFileList", loc_allNameFileList);
		
		return "/view/main/main.jsp";
	}
	
}
