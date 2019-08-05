package action.loc;

import java.io.IOException;
import java.io.PrintWriter;
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
		String loc_name = request.getParameter("loc_name");
		RevDAO dao = RevDAO.getInstance();
		RevVO vo = new RevVO();
		
		HttpSession session = request.getSession();
		String rev_writer = (String)session.getAttribute("member");
		String loc_contents = request.getParameter("loc_contents");
		int rev_score = Integer.parseInt(request.getParameter("rev_score"));
		String rev_locname = request.getParameter("rev_locname");
		
		vo.setRev_date(new Timestamp(System.currentTimeMillis()));
		vo.setRev_writer(rev_writer);
		vo.setRev_contents(loc_contents);
		vo.setRev_alive(1);
		vo.setRev_score(rev_score);
		vo.setRev_locname(rev_locname);
		
		int result = dao.insert(vo);
		if(result > 0) {
			PrintWriter out=response.getWriter();
	   		out.println("<script>");
	   		out.println("alert('등록 성공');");
	   		out.println("</script>");
	   		out.close();
		} else {
			PrintWriter out=response.getWriter();
	   		out.println("<script>");
	   		out.println("alert('등록 실패');");
	   		out.println("</script>");
	   		out.close();
		}
		
		return "view/loc/locView.do?loc_name="+loc_name;
	}

}
