package action.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
      if(day < 10) d = "0" + day;
      String today = year + "" + m + "" + d;
      
      String path = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=kRIhErX5mXekrgcMNZecYnVvmluclGMM&searchdate=" + today + "&data=AP01";
      
      try {
         URL url = new URL(path);
         HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
         urlConnection.setRequestMethod("GET");
         BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
         String result = br.readLine();
         
         response.setContentType("text/html;charset=UTF-8"); 
         response.getWriter().print(result);
         
      } catch(IOException e) {
         e.printStackTrace();
      }
       
   }
}