package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Command;
import model.mem.MemDAO;
import model.mem.MemVO;

public class MemUpdatePro implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String saveDirectory = request.getRealPath("/view/mem/upload");
//		int maxPostSize = 8*1024*1024;
//		MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", new DefaultFileRenamePolicy());
		String mem_id = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");
		String calc = request.getParameter("colc");
		String mem_value = request.getParameter("mem_value");
		
//		String mem_filename = multi.getFilesystemName((String)multi.getFileNames().nextElement());
		int check = MemDAO.getInstance().update(mem_id, mem_pwd, calc, mem_value);
		System.out.println(check);
		response.getWriter().print("<result><t>true</t></result>");
//		if(check > 0) {
//			return "/memUpdateForm.do";
//		} else {
//			return "";
//		}
		
		return null;
	}

}
