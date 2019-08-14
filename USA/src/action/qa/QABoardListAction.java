package action.qa;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.qa.QABoardDAO;
import model.qa.QABoardVO;

public class QABoardListAction implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		
		
		//한번에 볼 페이지의 수
		  int pageSize=5;
		  //10개씩 보기 20개씩 보기등이 눌렸다면. PAGEZIE값을 재정의
		  if(request.getParameter("pagesize")!=null&&request.getParameter("pagesize")!=""){
			  pageSize=Integer.parseInt(request.getParameter("pagesize"));
		  }
		  //글의 시작 끝 번호를 구하기 위한것.
		  int pageSizeref=pageSize-1;
		  

		  //최신페이지 구하기전에 1로 셋팅
		    int qaBoardCurrentPage = 1;
		  //지금 몇페이지인지.
	        String page = request.getParameter("page");
	      //삽니다인지 팝니다인지.

	        if(page != null&&page!="")  qaBoardCurrentPage = Integer.parseInt(page);
	        //     페이지 시작 글번호             최근페이지*한번에 볼 페이지- (한번에볼페이지-1) 이러면 첫페이지를 구할수가 있음.
	        int qaStartNum=qaBoardCurrentPage*pageSize-pageSizeref; //시작 글번호
	        
	        //시작번호에 endnum을 더해주기 위해 만들었음.
	        int qaEndNum=pageSize; //끝번호 사실상 의미없음. pageSize와 같은값.
	        
	        //검색 옵션 0제목 1 제목+내용 2 글쓴이
	        String opt = request.getParameter("opt");
	        //검색조건
	        String condition=request.getParameter("condition");
	        //한글검색시 꺠짐떄문에 처리
	        if(condition!=null) {
	        	
	        	condition=URLDecoder.decode(request.getParameter("condition"),"UTF-8");
	        }
	        
	        
	        
	        
	        //검색조건,검색내용,시작페이지 글번호 hashmap에 담음 변수명 여러개적기가 힘들어서.
	       HashMap<String, Object> listOpt = new HashMap<String, Object>();
	        listOpt.put("opt", opt);
	        listOpt.put("condition", condition);
	        //첫 시작 글번호 
	        listOpt.put("start", qaStartNum);
	        listOpt.put("end",qaEndNum);
	        QABoardDAO dao = QABoardDAO.getInstance();
	        
	        //전체 게시물숫자 조회
	        int listCount = dao.getBoardListCount(listOpt);
	        //,시작 글 번호 사용안하고있음? 왜냐하면 위의 쿼리에서 알아서 걸러버림. 지울것.
	        int qaBoardNumberSize=listCount-(qaBoardCurrentPage-1) *pageSize;
	        
	        
	        
	        //게시물 내용 담음
	        ArrayList<QABoardVO> list =  dao.getBoardList(listOpt);

	        

	        
	        
	        //한번에 보여줄 페이징의 수
	        int pageBlock=5;
	        
	        //전체 페이지 크기
	        int pageCount = listCount / pageSize + ( listCount % pageSize == 0 ? 0 : 1 );
	       
	        
	        int startPage = (int)((qaBoardCurrentPage-1)/pageBlock) * pageBlock + 1;
	        int endPage = startPage +pageBlock- 1;
	        
	        //마지막보여줄 페이지보다 총 페이지수보다 작으면 마지막보여줄 페이지를 총페이지수로.
	        if(endPage > pageCount)    endPage = pageCount;
	        
	        
	        
	      
	      
			
	        
	        
	        
	        
	        
	        
	        
	        
	        //페이지 관련 정보 보내줌.
	        request.setAttribute("qaBoardCurrentPage", qaBoardCurrentPage);
	        request.setAttribute("pageCount", pageCount);
	        request.setAttribute("startPage", startPage);
	        request.setAttribute("endPage", endPage);
	        request.setAttribute("pageBlock", pageBlock);
	        request.setAttribute("pagesize", pageSize);

	        //전체 게시물  게시물 내용 분류
	        request.setAttribute("listCount", listCount);
	        request.setAttribute("list", list);
	        
	        //검색조건이 걸려있으면 셋팅해서 보내줌.
	        if(opt!=null) {
	        request.setAttribute("opt", opt);
	        request.setAttribute("condition", condition);
	       }
			return "/view/qa/list.jsp";
	        
	      	        
	       
	       
	    }

}
