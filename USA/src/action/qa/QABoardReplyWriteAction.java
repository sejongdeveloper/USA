package action.qa;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.qa.QABoardreplyDAO;
import model.qa.QABoardreplyVO;

public class QABoardReplyWriteAction extends HttpServlet {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		QABoardreplyDAO dao = new QABoardreplyDAO();
		QABoardreplyVO vo = new QABoardreplyVO();
		boolean result = false;
		PrintWriter writer = response.getWriter();
		// 받을떄

		String jsoninfo = request.getParameter("data");
		JSONParser jsonParser = new JSONParser();

		try {

			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsoninfo);
			// json값 가져올떄 long형으로 가져옴
		
			// 댓글 고유번호
			int qarepnum = dao.getreplynum();
			// 원게시판번호
			int qarepqanum = (int) (long) jsonObject.get("qanum");
			// 내용
			String qarepContent = (String) jsonObject.get("content");
			// 작성자 닉네임으로설정.
			String qarepWriter = (String) request.getSession().getAttribute("nickname");
			// 답글인지 판단 0이면 원글 1이면 답글.
			int ref = Integer.parseInt((String) jsonObject.get("ref"));

			int qarepWriternum = qarepnum; // 부모그룹을 만들어주는데 그것은 자기자신의 고유번호로 지정
			// ref 0이면 답글아니라는 의미 즉 댓글고유번호로 부모그룹을 만들어줌.
			if (ref == 0)
				qarepWriternum = qarepnum;
			// 대댓글달떄 누구의 대댓글인지 알려주기위함.
			String qareprepwriter = null;
			int qarepNumlv = 0;
			if (ref == 1) {
				qareprepwriter = (String) jsonObject.get("wrtierrepwriter");// 누구의 대댓글인지.
				qarepWriternum = (int) (long) jsonObject.get("writernum");// 부모작성자번호
				qarepNumlv = (int) (long) jsonObject.get("numlv"); // 답글순서

			}
//		
			// 1댓글 고유번호
			vo.setqarep_num(qarepnum);
			// 2게시판 번호
			vo.setqarep_qanum(qarepqanum);
			// 3내용
			vo.setqarep_contents(qarepContent);
			// 4작성자
			vo.setqarep_writer(qarepWriter);
			// 5부모작성자 번호
			vo.setqarep_writerrep(qarepWriternum);
			// 6.대댓글 유무
			vo.setqarep_numref(ref);
			// 6.1 대댓글이라면 누구의 대댓글인지
			vo.setqarep_writerrepwriter(qareprepwriter);
			// 7.대댓글 순서
			vo.setqarep_numref_lv(qarepNumlv);

			result = dao.insertReply(vo);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		String msg = null;
		if (!result) {
			msg = "댓글 등록에 실패하셨습니다.";
		}

// 댓글 성공인지 적는것
		JSONObject totalObject = new JSONObject();

		totalObject.put("result", msg);
		writer.print(totalObject);

	}
}