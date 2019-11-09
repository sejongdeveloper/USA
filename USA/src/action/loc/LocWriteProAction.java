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
import model.mem.MemDAO;

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
		
		// 포인트 증가
		MemDAO.getInstance().getPoint(rev_writer);
		
		vo.setRev_date(new Timestamp(System.currentTimeMillis()));
		vo.setRev_writer(rev_writer);
		vo.setRev_contents(rev_contents);
		vo.setRev_alive(1);
		vo.setRev_score(rev_score);
		vo.setRev_locname(rev_locname);
		
		// 리뷰 등록
		int result = dao.insert(vo);
		
		// 결과 출력 후 페이지 이동
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		if(result == 1) out.print("alert('작성 성공');");
		else out.print("alert('작성 실패');");
		out.print("location.href = 'locView.do?loc_name=" + rev_locname + "'");
		out.print("</script>");
		out.flush();
		
		return null;
	}

}
