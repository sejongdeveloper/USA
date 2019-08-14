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

public class QABoardReplyDeleteAction extends HttpServlet {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		QABoardreplyDAO dao = new QABoardreplyDAO();
		boolean result = false;
		PrintWriter writer = response.getWriter();
//		json을 사용하기위해 data를 담고. json형태로 바꾸기위한 jsonparser구현.
		String jsoninfo = request.getParameter("data");
		JSONParser jsonParser = new JSONParser();

		try {

			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsoninfo);

			// 댓글 고유번호만 가져옴.
			int qarepnum = (int) (long) jsonObject.get("qarepnum");
			result = dao.replydelete(qarepnum);
			// 그후삭제

		} catch (ParseException e) {
			e.printStackTrace();
		}
		// result가 false면 댓글등록에 실패했다고 메세지보내는것.
		String msg = null;
		if (!result) {
			msg = "댓글 등록에 실패하셨습니다.";
		}

		// 결과 저장을위해 만듬.
		JSONObject totalObject = new JSONObject();

		totalObject.put("result", msg);
		writer.print(totalObject);

	}
}