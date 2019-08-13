package action.loc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Command;
import model.loc.LocDAO;
import model.loc.LocVO;

public class LocListDeletePro implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String loc_regname = request.getParameter("loc_regname");
		String loc_name = request.getParameter("loc_name");
		
		int result = LocDAO.getInstance().deleteLocList(loc_name);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		if(result == 1) out.print("alert('삭제 성공');");
		else out.print("alert('글에 리뷰가 있어서 삭제할 수 없습니다.');");
		out.print("location.href = 'locList.do?loc_regname=" + loc_regname + "'");
		out.print("</script>");
		out.flush();
		
		return null;
	}

}
