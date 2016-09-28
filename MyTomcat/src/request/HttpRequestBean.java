package request;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestBean {
	private Map<String, String> parametersMap;

	public HttpRequestBean() {
		parametersMap = new HashMap<String,String>();
	}
	
	public String getAttribute(String key){
		String value = "";
		value = parametersMap.get(key);
		return value;
	}
	
	public void setAttribute(String key, String value){
		parametersMap.put(key, value);
	}
}
