package action.tra;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.tra.TradeBoardDAO;
import model.tra.TradeBoardVO;
import model.tra.TradeBoardreplyDAO;
import model.tra.TradeBoardreplyVO;

public class TradeBoardDetailAction implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.setCharacterEncoding("UTF-8");
			// 게시판 내용에 대한 정보
			TradeBoardDAO dao = new TradeBoardDAO();
			TradeBoardVO vo = new TradeBoardVO();
			// 리플 dao불러옴.
			TradeBoardreplyDAO dao2 = new TradeBoardreplyDAO();
			// 리플리스트 담아오기위해 만듬.
			ArrayList<TradeBoardreplyVO> replylist = new ArrayList<TradeBoardreplyVO>();

			// 게시판 고유번호.
			int num = Integer.parseInt(request.getParameter("num"));

			// 게시판이 조회수 관리
			dao.updateCount(num);
			// 게시판 상세보기 가져오기.
			vo = dao.getDetail(num);
			replylist = dao2.getreplylist(num);

			// 게시판에 아무글도없다면 list로
			if (vo == null) {
				return "/view/tra/list.jsp";
			}

			request.setAttribute("vo", vo);
			request.setAttribute("replylist", replylist);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/view/tra/content.jsp";

	}
}
