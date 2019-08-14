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
		// 관광지 모든 데이터 가져오기
		LocVO loc_data = locdao.getLocContents(loc_name);

		request.setAttribute("loc_data", loc_data);
		
		String rev_locname = request.getParameter("loc_name");
		RevDAO revdao = RevDAO.getInstance();
		
		// 해당 관광지 리뷰 개수
		int rev_allCount = revdao.getAllCount(rev_locname);
		// 해당 관광지 총점
		double rev_allScore = revdao.getAllScore(rev_locname);
		// 해당 관관지 각 평점
		HashMap<Integer, Integer> rev_eachCount = revdao.getEachCount(rev_locname);
		// 해당 관공지 리뷰
		ArrayList<RevVO> rev_list = revdao.getRevContents(rev_locname);
		
		request.setAttribute("rev_allCount", rev_allCount);
		request.setAttribute("rev_allScore", String.format("%.1f", rev_allScore));
		request.setAttribute("rev_count5", rev_eachCount.get(5));
		request.setAttribute("rev_count4", rev_eachCount.get(4));
		request.setAttribute("rev_count3", rev_eachCount.get(3));
		request.setAttribute("rev_count2", rev_eachCount.get(2));
		request.setAttribute("rev_count1", rev_eachCount.get(1));
		request.setAttribute("rev_list", rev_list);
		
		return "/view/loc/locView.jsp";
	}

}
