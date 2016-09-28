package request;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MyRequestAnalysis {
	private InputStream input;

	public MyRequestAnalysis(InputStream input) {
			this.input = input;
	}
	// 获取请求并解析
	 public HttpRequestBean getRequestBean() {
		HttpRequestBean requestBean = new HttpRequestBean();
		try {
			System.out.println("*****************");
			
			char[] chars = new char[1024];
			InputStreamReader reader = new InputStreamReader(input);
			StringBuffer stringBuffer = new StringBuffer();
			while(true){
				reader.read(chars);
	            stringBuffer.append(new String(chars));
	            chars = null;
	            if(!reader.ready()) break;
			}
			//以换行符分割
			String[] stringArray = stringBuffer.toString().split("\\r\\n");
			System.out.println("解析报头：");
			for(int i = 0; i < stringArray.length; i ++){
				requestBean = this.ParserRequest(requestBean, stringArray[i]);
				System.out.println(stringArray[i]);
			}
			System.out.println("************");
			return requestBean;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return requestBean;
	}

	
	//解析请求
	public HttpRequestBean ParserRequest(HttpRequestBean requestBean, String string){
		if(string.startsWith("GET")){
			//解析首行方法
			requestBean.setAttribute("method", "Get");
			int index = string.indexOf("HTTP");
			
			String uriPata = string.substring(3 + 1, index -1 );
			//uri后带有参数
			if(uriPata.matches(".+\\?.+")){
				Pattern p = Pattern.compile("(.+)\\?(.+)");
				Matcher m = p.matcher(uriPata);
				while(m.find()){
					String uri = m.group(1);
					requestBean.setAttribute("uri", uri);
					String pata = m.group(2);
					Pattern p1 = Pattern.compile("(\\w+)=(\\w+)");
					Matcher m1 = p1.matcher(pata);
					while(m1.find()){
						requestBean.setAttribute(m1.group(1), m1.group(2));
					}
				}
			}else{
				requestBean.setAttribute("uri", uriPata);
			}
			String protocol = string.substring(index,index+8);
			requestBean.setAttribute("protocol",protocol);
		}else if(string.startsWith("POST")){
			//解析首行方法
			requestBean.setAttribute("method", "Post");
			int index = string.indexOf("HTTP");
			String uri = string.substring(4 + 1, index -1 );
			requestBean.setAttribute("uri", uri);
			String protocol = string.substring(index,index+8);
			requestBean.setAttribute("protocol",protocol);
		}else if(string.matches("\\w+[\\-\\w+]*\\: [\\w|\\W]+")){
			//解析头部信息
			Pattern p = Pattern.compile("(\\w+[\\-\\w+]*)\\: ([\\w|\\W]+)");
			Matcher m = p.matcher(string);
			while(m.find()){
				requestBean.setAttribute(m.group(1), m.group(2));
			}
		}else if(string.matches("\\w+\\=[\\w\\W]+")){
			//解析参数\\w+\\=\\w+(\\&\\w+\\=\\w+)*
			System.out.println("****开始解析参数***" + string);
			Pattern p = Pattern.compile("(\\w+)=(\\w+)");
			Matcher matcher = p.matcher(string);
			while(matcher.find()){
				requestBean.setAttribute(matcher.group(1), matcher.group(2));
			}
		}else{
			System.out.print("未知信息，无法解析:");
		}
		return requestBean;
	}
}
