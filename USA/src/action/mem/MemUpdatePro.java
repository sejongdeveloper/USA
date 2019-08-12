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
		System.out.println("회원정보 업데이트 ajax");
		String mem_id = request.getParameter("mem_id");
		String calc = request.getParameter("colc");
		String mem_value = request.getParameter("mem_value");
		
		int check = MemDAO.getInstance().update(mem_id, calc, mem_value);
		response.getWriter().print("<result>" + check + "</result>");
		
		return null;
	}

}
