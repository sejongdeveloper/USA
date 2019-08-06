package action.loc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.loc.RevDAO;
import model.loc.RevVO;

public class LocModifyProAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loc_name = request.getParameter("loc_name");
		RevDAO dao = RevDAO.getInstance();
		int rev_num = Integer.parseInt(request.getParameter("rev_num"));
		RevVO vo = dao.getUpdateVO(rev_num);
		
		int result = dao.update(vo);
		if(result > 0) {
			PrintWriter out=response.getWriter();
	   		out.println("<script>");
	   		out.println("alert('수정 성공');");
	   		out.println("</script>");
	   		out.close();
		} else {
			PrintWriter out=response.getWriter();
	   		out.println("<script>");
	   		out.println("alert('수정 실패');");
	   		out.println("</script>");
	   		out.close();
		}
		
		return "view/loc/locView.do?loc_name="+loc_name;
	}

}
