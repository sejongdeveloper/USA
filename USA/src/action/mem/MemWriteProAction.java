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

public class MemWriteProAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDirectory = request.getSession().getServletContext().getRealPath("/view/mem/upload");
		int maxPostSize = 5*1024*1024;
		
		try {
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", new DefaultFileRenamePolicy());
			String mem_id = multi.getParameter("mem_id");
			String mem_pwd = multi.getParameter("mem_pwd");
			String mem_ph = multi.getParameter("mem_ph");
			String mem_name = multi.getParameter("mem_name");
			String mem_addr = multi.getParameter("mem_addr");
			String mem_filename = multi.getFilesystemName((String)multi.getFileNames().nextElement());
			
			MemVO vo = new MemVO(mem_id, mem_pwd, mem_ph, mem_name, mem_addr, mem_filename);
			MemDAO.getInstance().insert(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/index.jsp";
		
	} // execute() end

}
