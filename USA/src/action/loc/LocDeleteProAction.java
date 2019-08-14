package action.loc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.loc.RevDAO;

public class LocDeleteProAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RevDAO dao = RevDAO.getInstance();
		int rev_num = Integer.parseInt(request.getParameter("rev_num"));
		
		// 번호에 해당하는 리뷰 삭제
		int result = dao.delete(rev_num);
		
		String rev_locname = request.getParameter("rev_locname");
		
		// 결과 출력 후 페이지 이동
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		if(result == 1) out.print("alert('삭제 성공');");
		else out.print("alert('삭제 실패');");
		out.print("location.href = 'locView.do?loc_name=" + rev_locname + "'");
		out.print("</script>");
		out.flush();
		
		return null;
	}

}
