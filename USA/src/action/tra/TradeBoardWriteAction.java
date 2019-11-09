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
		// 파일사이즈 설정.
		int fileSize = 5 * 1024 * 1024;
		// 작성자
		String replyWriter = null;
		// 업로드 경로
		String uploadPath = request.getServletContext().getRealPath("/upload");
		// 게시판번호 0으로 이랃ㄴ 할당.
		int tranum = 0;
		System.out.println(uploadPath + "//업로드폴더?");

		try {

			MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8",
					new DefaultFileRenamePolicy());

			String fileName = "";
			Enumeration<String> names = multi.getFileNames();
			if (names.hasMoreElements()) {
				String name = names.nextElement();
				fileName = multi.getFilesystemName(name);
			}

			TradeBoardDAO dao = TradeBoardDAO.getInstance();
			TradeBoardVO vo = new TradeBoardVO();
			// 게시판 업로드할시 필요한것은 게시판 고유번호 작성자 제목 내용 파일이름 분류가필요하니. 적어줌.
			tranum = dao.getSeq() + 1;
			vo.setTra_num(tranum);
			vo.setTra_writer(multi.getParameter("board_id"));
			vo.setTra_subject(multi.getParameter("board_subject").replaceAll("<", "&lt").replaceAll(">", "&gt"));
			vo.setTra_contents(multi.getParameter("board_content").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\r\n", "<br>"));
			vo.setTra_filename(fileName);
			vo.setTra_head(multi.getParameter("tra_head"));
			replyWriter = multi.getParameter("board_id");
			boolean result = dao.boardInsert(vo);

			MemDAO.getInstance().getPoint(replyWriter);
			if (result) {
				return "/view/tra/Tradecontent.do?num=" + tranum;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 등록 실패 " + e.getMessage());
		}

		return "/view/tra/Tradelist.do";
	}

}
