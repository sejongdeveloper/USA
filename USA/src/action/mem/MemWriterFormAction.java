package action.mem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;

public class MemWriterFormAction implements Command {

	// 회원가입 폼
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력폼이므로 request 초기화
		response.sendRedirect("view/mem/memWrite.jsp");
		return "";
	}

}
