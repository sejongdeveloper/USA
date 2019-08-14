package action.tra;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.tra.TradeBoardreplyDAO;
import model.tra.TradeBoardreplyVO;

public class TradeBoardReplyListAction extends HttpServlet {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		// 받을떄
		String jsoninfo = request.getParameter("data");
		JSONParser jsonParser = new JSONParser();
		int tranum = 0;// 초기화
		int currentpage = 1; // 최근페이지 초기화
		String pagestatus = "start";

		try {

			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsoninfo);

			// json값 가져올떄 int는 long형으로 가져옴

			// 게시판번호
			tranum = (int) (long) jsonObject.get("tranum");
			// 이동인지 글등록인지 게시판 처음시작한건지 판단.
			pagestatus = (String) jsonObject.get("pagestatus");

			// 최근페이지
			currentpage = Integer.parseInt((String) jsonObject.get("currentpage"));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		/// totalobject는 마지막에 담기위해 설정
//		replyarray는 vo라고 생각하면 편함. 
//		replyinfo는 개별저장.
		JSONObject totalObject = new JSONObject();
		JSONArray replyarray = new JSONArray();
		JSONObject replyinfo = new JSONObject();
		TradeBoardreplyDAO dao = new TradeBoardreplyDAO();
		TradeBoardreplyVO vo = new TradeBoardreplyVO();
		// 게시판 내용불러오는메소드
		ArrayList<TradeBoardreplyVO> list = new ArrayList<TradeBoardreplyVO>();

		// 아래부터는 페이지 변수 필요한것들.
		// 게시글 숫자를 구함
		list = dao.getreplylist(tranum);

		// 한번에 몇개의 게시물을 볼것인지
		int pageSize = 5;
		// 게시물 마지막 번호를 구하기 위한 변수
		int pageSizeref = pageSize - 1;
		/// 아래부터는 페이징처리 한번에 몇개의 블럭을 볼것인지.
		int pageBlock = 3;
		// 전체 몇개인지는 list.size()
		int listcount = list.size();
		// 전체 댓글 크기
		int pageCount = listcount / pageSize + (listcount % pageSize == 0 ? 0 : 1);
		// 시작댓글번호 구하기위한것.
		int startPage = (int) ((currentpage - 1) / pageBlock) * pageBlock + 1;
		// 한번에 볼 마지막 댓글숫자 구하는것.
		int endPage = startPage + pageBlock - 1;
		// 총 댓글보다 마지막댓글가 크면. 마지막페이지는 총페이지.
		if (pageCount < endPage)
			endPage = pageCount;
		// 페이지 상태가 start면 첫 로딩이라는 의미니 1
		if (pagestatus.equals("start")) {
			currentpage = 1;
			// insert일떄는 처음 댓글 등록할떄이니 가장 마지막 페이지로 이동.
		} else if (pagestatus.equals("insert")) {
			currentpage = pageCount;
		} // move일경우 값 변경이 없으니

		// 시작 댓글
		int TradeBoardReplystartnum = currentpage * pageSize - pageSizeref;

		// 한번에 볼 댓글페이지
		int TradeBoardReplypagenum = 5;

		int TradeBoardReplyendnum = TradeBoardReplystartnum + pageSize - 1;

		// 한번에 보여줄 댓글 게시글 내용 담기
		ArrayList<TradeBoardreplyVO> replylist = dao.getreplylist2(tranum, TradeBoardReplystartnum,
				TradeBoardReplyendnum);
		//

		JSONArray jsonpage = new JSONArray();
		JSONObject jsonpageobject = new JSONObject();
		jsonpageobject.put("pagecount", pageCount);
		jsonpageobject.put("startpage", startPage);
		jsonpageobject.put("endpage", endPage);
		jsonpageobject.put("pageblock", pageBlock);
		jsonpageobject.put("currentpage", currentpage);
		jsonpage.add(jsonpageobject);
		totalObject.put("page", jsonpage);

		// vo역할이라고 생각하면 됨.
		for (int i = 0; i < replylist.size(); i++) {
			replyinfo = new JSONObject();
			// 고유번호
			replyinfo.put("num", replylist.get(i).getTrarep_num());
			// 게시판번호
			replyinfo.put("tranum", replylist.get(i).getTrarep_tranum());
			// 내용
			replyinfo.put("content", replylist.get(i).getTrarep_contents());
			// 작성자
			replyinfo.put("writer", replylist.get(i).getTrarep_writer());
			// 답글일경우 답글작성자의 번호(그룹을 위함)
			replyinfo.put("trarep_writerrep", replylist.get(i).getTrarep_writerrep());
			// 답글이면 1아니면0
			replyinfo.put("numref", replylist.get(i).getTrarep_numref());
			// 몇번쨰 답글인지 레벨을 구함 높으면 가장 나중
			replyinfo.put("trarep_numref_lv", replylist.get(i).getTrarep_numref_lv());
			// 살아있는지
			replyinfo.put("alive", replylist.get(i).getTrarep_alive());
			// 누구의 답글인지 작성자이름을 저장
			replyinfo.put("trarep_writerrepwriter", replylist.get(i).getTrarep_writerrepwriter());
			// 게시판이 언제 씌여졌는지 저장.
			replyinfo.put("trarep_date", replylist.get(i).getTrarep_date());
			// vo라고 생각.
			replyarray.add(replyinfo);

		}

		totalObject.put("replyinfo", replyarray);
		totalObject.put("totalreply", list.size());

		// 네이버를 위해 세션값을 닉네임으로 저장.
		// total안에 vo역할을 하는 jsonsession을넣고 jsonobject는 각 내용을 의미 즉 세션저장하기위해만들었던것.
		String session = (String) request.getSession().getAttribute("nickname");
		JSONArray jsonsession = new JSONArray();
		JSONObject jsonobject = new JSONObject();
		jsonobject.put("session", session);
		jsonsession.add(jsonobject);
		totalObject.put("session", jsonsession);

		String jsonInfo = totalObject.toJSONString();
		writer.print(jsonInfo);

	}
}