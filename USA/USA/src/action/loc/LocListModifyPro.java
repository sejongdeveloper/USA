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

public class LocListModifyPro implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDirectory = request.getSession().getServletContext().getRealPath("/upload");
		int maxPostSize = 5*1024*1024;
		
		try {
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", new DefaultFileRenamePolicy());
			String loc_regname = multi.getParameter("loc_regname");
			String loc_name = multi.getParameter("loc_name");
			String loc_contents = multi.getParameter("loc_contents");
			String loc_filename = multi.getFilesystemName((String)multi.getFileNames().nextElement());
			
			LocVO vo = new LocVO();
			vo.setLoc_name(loc_name);
			vo.setLoc_contents(loc_contents);
			vo.setLoc_filename(loc_filename);			
			
			int result = LocDAO.getInstance().updateLocList(vo);
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			if(result == 1) out.print("alert('작성 성공');");
			else out.print("alert('작성 실패');");
			out.print("location.href = 'locList.do?loc_regname=" + loc_regname + "'");
			out.print("</script>");
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
