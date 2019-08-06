import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class Test {
	public static void main(String[] args) throws UnsupportedEncodingException, IOException, ParseException {
		String requestUrl = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=kRIhErX5mXekrgcMNZecYnVvmluclGMM&searchdate=20190806&data=AP01";
		URL url = new URL(requestUrl);
		InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "utf-8");
		JSONObject items = (JSONObject)JSONValue.parseWithException(isr);
		System.out.println(items);
		JSONArray bodyArray = (JSONArray)items.get("data");
		System.out.println(bodyArray);
	}
	
	
}
