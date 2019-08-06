package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.mem.MemDAO;
import model.mem.MemVO;

public class MemWritePro implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");
		String mem_ph = request.getParameter("mem_ph");
		String mem_name = request.getParameter("mem_name");
		String mem_addr = request.getParameter("mem_addr");
		String mem_filename = request.getParameter("mem_filename");
		System.out.println("mem_id : " + mem_id);
		MemVO vo = new MemVO(mem_id, mem_pwd, mem_ph, mem_name, mem_addr, mem_filename);
		int check = MemDAO.getInstance().insert(vo);
		
	} // execute() end

}
