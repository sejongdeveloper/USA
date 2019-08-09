
public class test {
	public static void main(String[] args) {
		String str = "1234/5678/dddd";
		System.out.println(str.indexOf("1234/5678"));
		System.out.println(str.substring(str.lastIndexOf("1234/5678") + 1));
	}
}
