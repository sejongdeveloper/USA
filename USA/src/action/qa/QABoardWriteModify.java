package action.qa;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Command;
import model.qa.QABoardDAO;
import model.qa.QABoardVO;

public class QABoardWriteModify implements Command {

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

			QABoardDAO dao = QABoardDAO.getInstance();
			QABoardVO vo = new QABoardVO();
			int qaBoardNum = dao.getSeq();
			vo.setqa_num(qaBoardNum);
			vo.setqa_writer(multi.getParameter("board_id"));
			vo.setqa_subject(multi.getParameter("board_subject"));
			vo.setqa_contents(multi.getParameter("board_content"));
			vo.setqa_filename(fileName);

			boolean result = dao.boardInsert(vo);

			if (result) {
				return "/view/qa/QAlist.do";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 등록 실패 " + e.getMessage());
		}

		return "/view/qa/QAlist.do";
	}

}
