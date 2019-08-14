package action.qa;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.qa.QABoardDAO;
import model.qa.QABoardVO;

public class QABoardModifyFormAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 게시판번호는 boardnum보기 편하게하기위해서 나눠서올렸음.
		String num = request.getParameter("num");
		int boardNum = Integer.parseInt(num);

		QABoardDAO dao = QABoardDAO.getInstance();
		QABoardVO vo;
		try { // vo에 보드내용을 담고. 보내줌. pagenum은 게시판 밑 게시물 리스트를 위해서 구현.
			vo = dao.getDetail(boardNum);
			request.setAttribute("vo", vo);
			request.setAttribute("pageNum", boardNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/view/qa/Modify.jsp";
	}

}
