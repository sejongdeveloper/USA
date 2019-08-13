package action.loc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.loc.RevDAO;
import model.loc.RevVO;

public class LocModifyProAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RevDAO dao = RevDAO.getInstance();
		RevVO vo = new RevVO();
		int rev_num = Integer.parseInt(request.getParameter("rev_num"));
		String rev_contents = request.getParameter("rev_contents");
		int rev_score = Integer.parseInt(request.getParameter("rev_modiscore"));
		
		vo.setRev_num(rev_num);
		vo.setRev_contents(rev_contents);
		vo.setRev_score(rev_score);
		
		int result = dao.update(vo);
		
		String rev_locname = request.getParameter("rev_locname");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		if(result == 1) out.print("alert('수정 성공');");
		else out.print("alert('수정 실패');");
		out.print("location.href = 'locView.do?loc_name=" + rev_locname + "'");
		out.print("</script>");
		out.flush();
		
		return null;
	}

}
