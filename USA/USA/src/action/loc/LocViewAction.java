package action.loc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.loc.LocDAO;
import model.loc.LocVO;
import model.loc.RevDAO;
import model.loc.RevVO;

public class LocViewAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loc_name = request.getParameter("loc_name");
		LocDAO locdao = LocDAO.getInstance();
		LocVO loc_data = locdao.getLocContents(loc_name);

		request.setAttribute("loc_data", loc_data);
		
		String rev_locname = request.getParameter("loc_name");
		RevDAO revdao = RevDAO.getInstance();
		int rev_allCount = revdao.getAllCount(rev_locname);
		double rev_allScore = revdao.getAllScore(rev_locname);
		HashMap<Integer, Integer> rev_eachCount = revdao.getEachCount(rev_locname);
		ArrayList<RevVO> rev_list = revdao.getRevContents(rev_locname);
		
		request.setAttribute("rev_allCount", rev_allCount);
		request.setAttribute("rev_allScore", String.format("%.1f", rev_allScore));
		request.setAttribute("rev_count5", rev_eachCount.get(5));
		request.setAttribute("rev_count4", rev_eachCount.get(4));
		request.setAttribute("rev_count3", rev_eachCount.get(3));
		request.setAttribute("rev_count2", rev_eachCount.get(2));
		request.setAttribute("rev_count1", rev_eachCount.get(1));
		request.setAttribute("rev_list", rev_list);
		
		String result = request.getParameter("result");
		request.setAttribute("result", result);
		
		return "/view/loc/locView.jsp";
	}

}
