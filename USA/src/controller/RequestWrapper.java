package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
 
public final class RequestWrapper extends HttpServletRequestWrapper {
   HttpServletRequest request;
 
    public RequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    public String[] getParameterValues(String parameter) {
 
      String[] values = super.getParameterValues(parameter);
      if (values==null)  {
                  return null;
          }
      int count = values.length;
      String[] encodedValues = new String[count];
      for (int i = 0; i < count; i++) {

         encodedValues[i] = cleanXSS(values[i]);
       }
      return encodedValues;
    }

    public String getParameter(String parameter) {
          String value = super.getParameter(parameter);
          
          if (value == null) {
        	  return null;
          }
          if(super.getParameter(parameter).equals("board_content")) System.out.println("노다지노다지노다지닷");
          
          return cleanXSS(value);
    }

    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null)	return null;
        
        return cleanXSS(value);
    }

    private String cleanXSS(String value) {
                //You'll need to remove the spaces from the html entities below
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        value = value.replaceAll("\r\n","<br>");
      
        if(value.contains("multipart/form")&&value.contains("WebKitFormBoundary")) {
           System.out.println(value);
        }

        return value;
    }
}

