package action.mem;

import javax.servlet.http.Cookie;

public class cookieTest {
	public static void main(String[] args) {
		Cookie cookie = new Cookie("member", "test1");
		cookie = new Cookie("member", "test2");
		System.out.println(cookie.getValue());
	}
}
