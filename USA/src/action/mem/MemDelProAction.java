package action.mem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Command;
import model.mem.MemDAO;

public class MemDelProAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 객체생성
		HttpSession session = request.getSession();
		// id 값
		String mem_id = (String)session.getAttribute("member");		
		// 네이버 로그인 연동하기 위한 연결토큰 값
		String access_token = (String)session.getAttribute("access_token");
		// 네이버 로그인 연동하기 위한 URL 값
		String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=fRezVk5_Htz8SWJjSj7d&client_secret=2tckaN8GEV&access_token=" + access_token + "&service_provider=NAVER";
		
		// 네이버 로그인 연동인 계정이 회원탈퇴 할 경우
		if(access_token != null) {
			try {
				//JAVA의 URL연결방식
				URL url = new URL(apiURL);
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("GET");
				int responseCode = con.getResponseCode();
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				
				if(responseCode == 200) {
					System.out.println("삭제성공");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// DB에 회원탈퇴 적용
		int check = MemDAO.getInstance().delete(mem_id);
		
		if(check > 0) {
			// session 끊기
			session.invalidate();
			return "/index.jsp";
		} else {
			return "";
		}
	}

}
