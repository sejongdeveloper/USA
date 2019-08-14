package action.reg;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.reg.RegDAO;
import model.reg.RegNameDAO;
import model.reg.RegNameVO;
import model.reg.RegVO;

public class RegMainAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegDAO dao = RegDAO.getInstance();
		
		String regname = request.getParameter("regname");
		// 지역의 파일명 가져오기
		ArrayList<RegVO> reg_filenameList = dao.getRegFileName(regname);
		
		request.setAttribute("regname", regname);
		request.setAttribute("reg_filenameList", reg_filenameList);
		
		RegNameDAO regnamedao = RegNameDAO.getInstance();
		// 지역의 이름, 시간차, 비행시간 가져오기
		RegNameVO regname_vo = regnamedao.getRegNameContents(regname);
		request.setAttribute("regname_vo", regname_vo);
		
		return "/view/reg/regMain.jsp";
	}

}
