package action.mem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import action.Command;
import model.mem.MemDAO;
import model.mem.MemVO;

public class MemNaverAction2 implements Command {

	// 네이버 로그인 연동 콜백함수
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 애플리케이션 클라이언트 아이디값
	    String clientId = "fRezVk5_Htz8SWJjSj7d";
	    // 애플리케이션 클라이언트 시크릿값
	    String clientSecret = "2tckaN8GEV";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    // 콜백함수 주소
	    String redirectURI = URLEncoder.encode("http://localhost:8080/USA/naver.do", "UTF-8");
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    
	    // 연결 토큰 선언
	    String access_token = "";
	    
	    // json.simple 라이브러리 객체생성
	    JSONParser parser = new JSONParser();
	    JSONObject json = null;
	    
	    try {
	      // JAVA URL 연결방식
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET"); // get방식 설정
	      
	      // 네트워크에서 전달받은 값을 얻기 위한 변수선언
	      BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())); 
	      
	      // 네이버에서 보내온 데이터를 읽어온 문자열
	      String inputLine; 
	      // 문자열을 새로 만들지 않고 기존 데이터에서 추가하는 형식
	      StringBuffer sb = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        sb.append(inputLine);
	      }
	      
	      // json 라이브러리를 통하여 연결 토큰값 얻기
	      parser = new JSONParser();
	      json = (JSONObject) parser.parse(sb.toString());
	      access_token = (String) json.get("access_token");
	      
	      br.close();
	    
	    } catch (Exception e) {
	      System.out.println(e);
	    }
	    
	   
	    
	    String header = null;
	     try {
	    	 // 네이버 로그인 접근 토큰
	    	 header = "Bearer " + access_token; // Bearer 다음에 공백 추가
	    	 
	         apiURL = "https://openapi.naver.com/v1/nid/me";
	         URL url = new URL(apiURL);
	         HttpURLConnection con = (HttpURLConnection)url.openConnection();
	         con.setRequestMethod("GET");
	         con.setRequestProperty("Authorization", header);
	         
	         BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	         
	         String inputLine;
	         StringBuffer sb = new StringBuffer();
	         while ((inputLine = br.readLine()) != null) {
	             sb.append(inputLine);           
	         }
	         br.close();
	         
	         // json으로 파싱하여 네이버 회원정보 얻기
	         JSONObject json2 = (JSONObject)parser.parse(sb.toString());
	         JSONObject info = (JSONObject)json2.get("response");
	         String id = (String)info.get("id");
	         String email = (String)info.get("email");
	         String profile_image = (String)info.get("profile_image");
	         String nickname = (String)info.get("nickname");
	         
	         // 아이디 존재여부 판단
	         boolean isId = MemDAO.getInstance().idValidate(id);
	         if(!isId) {
	        	 MemVO vo = new MemVO();
	        	 vo.setMem_pwd(email);
	        	 vo.setMem_id(id);
	        	 vo.setMem_name(nickname);
	        	 vo.setMem_filename(profile_image);
	        	 MemDAO.getInstance().insert(vo);
	         }
	         
	         request.getSession().setAttribute("member", id);
	         request.getSession().setAttribute("nickname", nickname);
	         
	     } catch (Exception e2) {
	         System.out.println(e2);
	     }
	     
	     return "/index.do";
	}

}
