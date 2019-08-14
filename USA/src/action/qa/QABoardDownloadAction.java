package action.qa;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;

public class QABoardDownloadAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 다운로드받을 파일네임을 불러와서.
		String fileName = request.getParameter("file_name");

		// realpath뒤에 실제 폴더 이름
		String UploadFolder = "upload";
		// 파일이 들어있는 경로를구함.
		String folder = request.getServletContext().getRealPath(UploadFolder);

		// 실제 파일의 정확한경로.
		String filePath = folder + "/" + fileName;

		try {
			File file = new File(filePath);
			byte b[] = new byte[(int) file.length()];
			// 응답 리셋후.
			response.reset();
			response.setContentType("application/octet-stream");
			// 한글파일꺠지는것 방지용.
			String encoding = new String(fileName.getBytes("euc-kr"), "8859_1");
			// 파일의 상세스펙 알려줌.
			response.setHeader("Content-Disposition", "attachment;filename=" + encoding);
			response.setHeader("Content-Length", String.valueOf(file.length()));

			if (file.isFile()) {

				// 파일이 있다면. 클라이언트와 소통경로를 열어줌.
				FileInputStream fileInputStream = new FileInputStream(file);
				ServletOutputStream servletOutputStream = response.getOutputStream();

				int readNum = 0;
				// 그후 파일을 다읽었다는 의미인 -1일떄까지 쭉 읽어옴.
				while ((readNum = fileInputStream.read(b)) != -1) {
					servletOutputStream.write(b, 0, readNum);
				}

				servletOutputStream.close();
				fileInputStream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
