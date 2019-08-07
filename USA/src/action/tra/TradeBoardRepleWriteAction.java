package action.tra;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import model.tra.TradeBoardRepleDAO;
import model.tra.TradeBoardRepleVO;

public class TradeBoardRepleWriteAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TradeBoardRepleDAO dao = TradeBoardRepleDAO.getInstance();
		TradeBoardRepleVO vo= new TradeBoardRepleVO();
		
		// �ĸ����� ���� �����´�.
		int comment_board = Integer.parseInt(request.getParameter("comment_board"));
		String comment_id = request.getParameter("comment_id");
		String comment_content = request.getParameter("comment_content");
		
		vo.setComment_num(dao.getSeq());	// ��� ��ȣ�� ������������
		vo.setComment_board(comment_board);
		vo.setComment_id(comment_id);
		vo.setComment_content(comment_content);
		
		boolean result = dao.insertReple(vo);

		if(result){
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("1");
			out.close();
		return null;
	}
		
		
		return null;
	}
}


	
