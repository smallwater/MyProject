package response;

import java.util.HashMap;
import java.util.Map;

public class HttpResponseBean {
	private String responseState;//响应状态设置
	private Map<String, String> mapList = new HashMap<String,String>(); //响应头的参数
	
	//设置返回状态
	public void setResponseState(String state){
		this.responseState = state;
//		if(state.equals("OK")){
//			responseState = "HTTP/1.1 200 OK";
//		}else if(state.equals("NotFound")){
//			responseState = "HTTP/1.1 404 Not Found";
//		}else if(state.equals("Error")){
//			responseState = "HTTP/1.1 500 Internal Server Error";
//		}
	}
	
	public void setResponseParameter(String name,String value){
		mapList.put(name, value);
	}
	
	public String getResponseHeader(){
		StringBuffer responseHeader = new StringBuffer();
		if(responseState != null){
			responseHeader.append(responseState + "\n");
		}else{
			System.out.println("未设置响应状态");
		}
		
		if(mapList != null){
			for(Map.Entry entry:mapList.entrySet()){
				responseHeader.append(entry.getKey() + ": " + entry.getValue() + "\n");
			}
		}else{
			System.out.println("未设置响应参数");
		}
		
		return responseHeader.toString();
	}
}
