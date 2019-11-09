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
			
			LocDAO dao = LocDAO.getInstance();
			
			LocVO vo = new LocVO();
			vo.setLoc_name(loc_name);
			vo.setLoc_contents(loc_contents);
			
			// null이면 기존 파일명 그대로 입력
			if(loc_filename == null) {
				vo.setLoc_filename(dao.getLocFile(loc_name));
			} else {
				vo.setLoc_filename(loc_filename);			
			}
			
			// 관광지 수정
			int result = dao.updateLocList(vo);
			
			// 결과 출력 후 페이지 이동
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
