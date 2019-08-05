package action.tra;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;
import model.tra.TradeBoardDAO;
import model.tra.TradeBoardVO;

public class TradeBoardListAction implements Command {

	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		
		
//		ActionForward forward = new ActionForward();
	        //�ѹ��� ������ ������
		  int pageSize=2;
		  
	        // ���� ������ ��ȣ �����
	        int spage = 1;
	        String page = request.getParameter("page");
	        
	        if(page != null)
	            spage = Integer.parseInt(page);
	        
	        // �˻����ǰ� �˻������� �����´�.
	        

	        String opt = request.getParameter("opt");
	        
	        String condition=request.getParameter("condition");
	        
	        if(condition!=null) {
	        	
	        	condition=URLDecoder.decode(request.getParameter("condition"),"euc-kr");
	        }
	        
	       System.out.println(condition);
	        
	        
	        
	        
	        // �˻����ǰ� ������ Map�� ��´�.
	        HashMap<String, Object> listOpt = new HashMap<String, Object>();
	        listOpt.put("opt", opt);
	        listOpt.put("condition", condition);
	        listOpt.put("start", spage*5-4);
	        
	        TradeBoardDAO dao = TradeBoardDAO.getInstance();
	        int listCount = dao.getBoardListCount(listOpt);
	        ArrayList<TradeBoardVO> list =  dao.getBoardList(listOpt);
	        
	        // �� ȭ�鿡 3���� �Խñ��� ����������
	        // ������ ��ȣ�� �� 2��, ���ķδ� [����]���� ǥ��
	        
	        // ��ü ������ ��
	        int maxPage = (int)(listCount/5.0 + 0.2);
	        //���� ������ ��ȣ
	        int startPage = (int)(spage/5.0 + 0.8) * 2 - 1;
	        //������ ������ ��ȣ
	        int endPage = startPage + 1;
	        if(endPage > maxPage)    endPage = maxPage;
	        
	        
	        
	      
	      
			
	        
	        
	        
	        
	        
	        
	        //�ѹ��� ������ ������ 
	        request.setAttribute("pageSize", pageSize);
	        // 4�� ��������ȣ ����
	        request.setAttribute("spage", spage);
	        request.setAttribute("maxPage", maxPage);
	        request.setAttribute("startPage", startPage);
	        request.setAttribute("endPage", endPage);
	        
	        // ���� �� ���� �۸�� ����
	        request.setAttribute("listCount", listCount);
	        request.setAttribute("list", list);
	        
	        //�˻����� ������ ���� �˻����ǰ� �˻������� �ٽ� ������.
	       if(opt!=null) {
	        request.setAttribute("opt", opt);
	        request.setAttribute("condition", condition);
	       }
	        
	        // �ܼ� ��ȸ�̹Ƿ� forward()��� (= DB�� ���º�ȭ �����Ƿ�) 
	      
	        
	        
	       
	    }


}



