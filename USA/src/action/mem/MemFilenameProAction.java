package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Command;
import model.mem.MemDAO;

public class MemFilenameProAction implements Command {

	// 회원사진 변경 실행
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 저장 위치
		String saveDirectory = request.getSession().getServletContext().getRealPath("/view/mem/upload");
		System.out.println("saveDirectory: " + saveDirectory);
		// 파일 최대 크기
		int maxPostSize = 1024*1024*8;
		// 파일 라이브러리(cos.jar) 객체생성
		// new DefaultFileRenamePolicy()란 중복 파일이름 구분하여 저장하는 방식 (ex: fileName.jpg, fileName1.jpg) 
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", new DefaultFileRenamePolicy());
		// 아이디 값
		String mem_id = (String)request.getSession().getAttribute("member");
		// 파일이름 값
		String mem_filename = multi.getFilesystemName((String)multi.getFileNames().nextElement());
		
		MemDAO.getInstance().update(mem_id, "mem_filename", mem_filename);
		// 입력폼이므로 리다이렉트 방식적용(request 초기화)
		response.sendRedirect("/USA/memUpdateForm.do");
		return null;
	}

}
