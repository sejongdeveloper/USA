package action.tra;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Command;
import model.tra.TradeBoardDAO;
import model.tra.TradeBoardVO;

public class TradeBoardWriteModify implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int fileSize = 5 * 1024 * 1024;

		String uploadPath = request.getServletContext().getContextPath();
		request.getServletContext().getContextPath();
		request.setAttribute("uploadPath", uploadPath);

		try {

			MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize, "euc-kr",
					new DefaultFileRenamePolicy());

			String fileName = "";
			Enumeration<String> names = multi.getFileNames();
			if (names.hasMoreElements()) {
				String name = names.nextElement();
				fileName = multi.getFilesystemName(name);
			}

			TradeBoardDAO dao = TradeBoardDAO.getInstance();
			TradeBoardVO vo = new TradeBoardVO();
			int TraBoardNum = dao.getSeq();
			vo.setTra_num(TraBoardNum);
			vo.setTra_writer(multi.getParameter("board_id"));
			vo.setTra_subject(multi.getParameter("board_subject"));
			vo.setTra_contents(multi.getParameter("board_content"));
			vo.setTra_filename(fileName);

			boolean result = dao.boardInsert(vo);

			if (result) {
				return "/view/tra/list.do";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 등록 실패 " + e.getMessage());
		}

		return "/view/tra/list.do";
	}

}
