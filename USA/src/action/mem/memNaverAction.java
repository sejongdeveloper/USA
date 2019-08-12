package action.mem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import action.Command;

public class memNaverAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clientId = "fRezVk5_Htz8SWJjSj7d";//애플리케이션 클라이언트 아이디값";
	    String redirectURI = URLEncoder.encode("http://localhost:8080/USA/view/mem/naver.jsp", "UTF-8");
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;
	    request.getSession().setAttribute("state", state);
	    
	    
	    
	    String clientSecret = "2tckaN8GEV";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_token = "";
	    String refresh_token = "";
	    
	    String str = null;
	    
	    try {
	        URL url = new URL(apiURL);
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("GET");
	        int responseCode = con.getResponseCode();
	        BufferedReader br;
	        System.out.print("responseCode="+responseCode);
	        if(responseCode==200) { // 정상 호출
	          br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        } else {  // 에러 발생
	          br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	        }
	        String inputLine;
	        StringBuffer res = new StringBuffer();
	        while ((inputLine = br.readLine()) != null) {
	          res.append(inputLine);
	        }
	        str = res.toString();
	        br.close();
	      } catch (Exception e) {
	        System.out.println(e);
	      }
	    
	    
	    
	    JSONParser parser = new JSONParser();
	    JSONObject json = null;
	    String token = null;
	    String header = null;
	     try {
	    	 json = (JSONObject)parser.parse(str);
	    	 token = (String)json.get("access_token");// 네이버 로그인 접근 토큰; 여기에 복사한 토큰값을 넣어줍니다.
	    	 
	    	 header = "Bearer " + token; // Bearer 다음에 공백 추가
	         String apiURL2 = "https://openapi.naver.com/v1/nid/me";
	         URL url = new URL(apiURL2);
	         HttpURLConnection con = (HttpURLConnection)url.openConnection();
	         con.setRequestMethod("GET");
	         con.setRequestProperty("Authorization", header);
	         int responseCode = con.getResponseCode();
	         BufferedReader br;
	         if(responseCode==200) { // 정상 호출
	             br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	         } else {  // 에러 발생
	             br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	         }
	         String inputLine;
	         StringBuffer response2 = new StringBuffer();
	         while ((inputLine = br.readLine()) != null) {
	             response2.append(inputLine);           
	         }
	         br.close();
	         
	         JSONObject json2 = (JSONObject)parser.parse(response2.toString());
	         JSONObject info = (JSONObject)json2.get("response");
	         String id = (String)info.get("id");
	         String email = (String)info.get("email");
	         String profile_image = (String)info.get("profile_image");
	         String name = (String)info.get("name");
	         System.out.println("==============================");
	         System.out.println("id : " + id);
	         System.out.println("email : " + email);
	         System.out.println("profile_image : " + profile_image);
	         System.out.println("name : " + name);
	         System.out.println("==============================");
	         
	         System.out.println(response2.toString());
	     } catch (Exception e2) {
	         System.out.println(e2);
	     }
		return null;
	}

}
