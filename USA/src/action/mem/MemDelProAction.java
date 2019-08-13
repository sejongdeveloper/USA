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
		HttpSession session = request.getSession();
		String mem_id = (String)session.getAttribute("member");
		
		String access_token = (String)request.getSession().getAttribute("access_token");
		String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=fRezVk5_Htz8SWJjSj7d&client_secret=2tckaN8GEV&access_token=" + access_token + "&service_provider=NAVER";
		try {
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
		
		
		int check = MemDAO.getInstance().delete(mem_id);
		if(check > 0) {
			session.invalidate();
			return "/index.jsp";
		} else {
			return "";
		}
	}

}
