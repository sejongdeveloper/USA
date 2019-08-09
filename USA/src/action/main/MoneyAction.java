package action.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.mem.MemVO;

public class MoneyAction {
	public static void execute(HttpServletRequest request ,HttpServletResponse response) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH) - 1;
		String m = String.valueOf(month);
		if(month < 10) m = "0" + month;
		String d = String.valueOf(day);
		if(month < 10) d = "0" + day;
		String today = year + "" + m + "" + d;
		System.out.println(today);
		String path = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=kRIhErX5mXekrgcMNZecYnVvmluclGMM&searchdate=" + today + "&data=AP01";
		System.out.println(path);
		try {
			URL url = new URL(path);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));
			String result = br.readLine();			
			
			JSONParser jsonParser = new JSONParser();
			JSONArray arr =(JSONArray) jsonParser.parse(result);
			
			JSONObject info = (JSONObject) arr.get(21); 
			String cur_nm = (String)info.get("cur_nm");
			String bkpr = (String)info.get("bkpr");	
			System.out.println("이름: " + cur_nm);
			System.out.println("환율: " + bkpr);
			
			response.setContentType("text/html; charset=utf-8");
			MemVO vo = new MemVO();
			vo.setMem_addr("테스트주소");
			vo.setMem_id("테스트아이디");
		response.getWriter().print(result);
			
			
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ParseException e) {
			e.printStackTrace();
		}
		 
	}
}
