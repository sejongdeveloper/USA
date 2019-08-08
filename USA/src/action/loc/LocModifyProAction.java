package action.loc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.loc.RevDAO;
import model.loc.RevVO;

public class LocModifyProAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RevDAO dao = RevDAO.getInstance();
		int rev_num = Integer.parseInt(request.getParameter("rev_num"));
		RevVO vo = dao.getUpdateVO(rev_num);
		
		int result = dao.update(vo);
		
		String rev_locname = request.getParameter("loc_name");
		
		return request.getContextPath() + "/view/reg/locView.do?loc_name="+URLEncoder.encode(rev_locname, "utf-8")+"&result="+result;
	}

}
