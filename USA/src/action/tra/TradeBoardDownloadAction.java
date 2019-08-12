package action.tra;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;

public class TradeBoardDownloadAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fileName = request.getParameter("file_name");

		//realpath뒤에 실제 폴더 이름
		String UploadFolder="upload";
		String folder = request.getServletContext().getRealPath(UploadFolder);
		
		
		
		String filePath = folder + "/" + fileName;
		System.out.println(filePath);
		try {
			File file = new File(filePath);
			byte b[] = new byte[(int) file.length()];
			
			response.reset();
			response.setContentType("application/octet-stream");
			
			String encoding = new String(fileName.getBytes("euc-kr"),"8859_1");
			
			response.setHeader("Content-Disposition", "attachment;filename="+ encoding);
			response.setHeader("Content-Length", String.valueOf(file.length()));
			
			if (file.isFile()) 
			{
				FileInputStream fileInputStream = new FileInputStream(file);
				ServletOutputStream servletOutputStream = response.getOutputStream();
				
				int readNum = 0;
				while ( (readNum = fileInputStream.read(b)) != -1) {
					servletOutputStream.write(b, 0, readNum);
				}
				
				servletOutputStream.close();
				fileInputStream.close();
			}
			
		} catch (Exception e) {
			System.out.println("Download Exception : " + e.getMessage());
		}

	
		
		
		
		
		
		return null;
	}

}
