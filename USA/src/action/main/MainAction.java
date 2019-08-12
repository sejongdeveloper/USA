package action.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.loc.LocDAO;
import model.loc.LocVO;
import model.tra.TradeBoardDAO;
import model.tra.TradeBoardVO;

public class MainAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		LocDAO locdao = LocDAO.getInstance();
		
		ArrayList<LocVO> loc_allNameFileList = locdao.getAllLocFileName();
		
		
		//아래부터는 리스트 처리
		TradeBoardDAO dao=new TradeBoardDAO();
		ArrayList<TradeBoardVO> sellvo=new ArrayList<TradeBoardVO>();
		ArrayList<TradeBoardVO> buyvo=new ArrayList<TradeBoardVO>();
		sellvo=dao.getMainBoardList("팝니다");
		buyvo=dao.getMainBoardList("삽니다");
		
		
		request.setAttribute("sellvo", sellvo);
		request.setAttribute("buyvo", buyvo);
//로케이션		
		
		request.setAttribute("loc_allNameFileList", loc_allNameFileList);
		
		
		
		
		return "/view/main/main.jsp";
	}
	
}
