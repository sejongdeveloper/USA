package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Command;
import model.mem.MemDAO;

public class MemFilenameProAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDirectory = request.getSession().getServletContext().getRealPath("/view/mem/upload");
		int maxPostSize = 1024*1024*8;
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, new DefaultFileRenamePolicy());
		String mem_id = (String)request.getSession().getAttribute("member");
		String mem_filename = multi.getFilesystemName((String)multi.getFileNames().nextElement());
		System.out.println("id:" + mem_id + " filename:" + mem_filename);
		MemDAO.getInstance().update(mem_id, "mem_filename", mem_filename);
		response.sendRedirect("/USA/memUpdateForm.do");
		return null;
	}

}
