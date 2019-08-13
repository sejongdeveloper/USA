package action.tra;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Command;
import model.mem.MemDAO;
import model.tra.TradeBoardDAO;
import model.tra.TradeBoardVO;

public class TradeBoardWriteAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int fileSize= 5*1024*1024;
		String rev_writer=null;
		String uploadPath = request.getServletContext().getRealPath("/upload");
		int num=0;
		System.out.println(uploadPath +"//업로드폴더?");
		
		
		try {
			
			MultipartRequest multi = new MultipartRequest
					(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());

			String fileName = "";
			Enumeration<String> names = multi.getFileNames();
			if(names.hasMoreElements())
			{
				String name = names.nextElement();
				fileName = multi.getFilesystemName(name);
			}
			
			TradeBoardDAO dao = TradeBoardDAO.getInstance();
			TradeBoardVO vo = new TradeBoardVO();
			num=dao.getSeq()+1;
			vo.setTra_num(num);
			vo.setTra_writer(multi.getParameter("board_id"));
			vo.setTra_subject(multi.getParameter("board_subject"));
			vo.setTra_contents(multi.getParameter("board_content"));
			vo.setTra_filename(fileName);
			vo.setTra_head(multi.getParameter("tra_head"));
			rev_writer=multi.getParameter("board_id");
			boolean result = dao.boardInsert(vo);
			
			MemDAO.getInstance().getPoint(rev_writer);
			if(result){
				return "/view/tra/content.do?num="+num;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 등록 실패 " + e.getMessage());
		}
		 
		
		
		return "/view/tra/list.do";
	}

}
