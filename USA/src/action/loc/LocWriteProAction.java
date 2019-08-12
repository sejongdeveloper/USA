package action.loc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Command;
import model.loc.RevDAO;
import model.loc.RevVO;

public class LocWriteProAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getContextPath();
		RevDAO dao = RevDAO.getInstance();
		RevVO vo = new RevVO();

		HttpSession session = request.getSession();
		String rev_writer = (String)session.getAttribute("member");
		String rev_contents = request.getParameter("rev_contents");
		int rev_score = Integer.parseInt(request.getParameter("rev_score"));
		String rev_locname = request.getParameter("rev_locname");
		
		vo.setRev_date(new Timestamp(System.currentTimeMillis()));
		vo.setRev_writer(rev_writer);
		vo.setRev_contents(rev_contents);
		vo.setRev_alive(1);
		vo.setRev_score(rev_score);
		vo.setRev_locname(rev_locname);
		
		int result = dao.insert(vo);
		
		return request.getContextPath() + "/view/reg/locView.do?loc_name="+URLEncoder.encode(rev_locname, "utf-8")+"&result="+result;
	}

}
