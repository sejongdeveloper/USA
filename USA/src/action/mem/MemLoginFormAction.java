package action.mem;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Command;

public class MemLoginFormAction implements Command {

	// // 로그인 폼
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 아이디를 저장한 쿠키값이 있는지 판단
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("mem_id")) {
					request.setAttribute("cookie_mem_id", cookie.getValue());
					break;
				}
			}
			
		}
		
		// 네이버 로그인 연동하기 위한 기본설정
		String clientId = "fRezVk5_Htz8SWJjSj7d";//애플리케이션 클라이언트 아이디값";
	    String redirectURI = URLEncoder.encode("http://localhost:8080/USA/naver.do", "UTF-8");	    
	    // 랜덤한 값으로 state 초기화
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;
	    // 콜백함수에 쓰일 state
	    request.getSession().setAttribute("state", state);
	    // 로그인 폼에만 쓰일 apiURL
		request.setAttribute("apiURL", apiURL);
		return "/view/mem/memLogin.jsp";
	}

}
