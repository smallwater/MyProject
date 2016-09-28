package response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import util.Constants;

public class MyResponseAnalysis {
	private OutputStream output;
	private HttpResponseBean ResponseBean;
	private String uri;

	public MyResponseAnalysis(OutputStream output, String uri) {
		this.output = output;
		this.uri = uri;
		this.ResponseBean = getHttpResponseByUri(uri);;
	}
	
	//发送响应头
	public void sendHttpResponseHeader(){
		String headString = ResponseBean.getResponseHeader();
		if(headString != null){
			sendResponse(headString);
		}else{
			System.out.println("未设置响应头");
		}
	}
	
	public void SendStaticFile(){
		
		// 获取要传送的文件
		File file = new File(Constants.WEB_ROOT, uri);//具体的html文件
		if(file.exists()){
			//发送文件
			sendFile(file);
		}else{
			System.out.println("找不到文件，文件发送失败");
		}
	}

	// 传送文件
	synchronized public void sendFile(File file) {
		try {
			byte[] bytes = new byte[1024 * 4];
			FileInputStream fis = new FileInputStream(file);
			int length = 0;
			System.out.println("开始读取文件");
			while ((length = fis.read(bytes)) != -1) {
				output.write(bytes, 0, length);
				output.flush();
				System.out.println(new String(bytes));
			}
			System.out.println("文件传送完成");
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("文件未发现");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件传送失败，请重新传送");
		}
	}
	// 传送响应头
	public void sendResponse(String headString) {
		try {
			byte[] bytes = new byte[1024 * 4];
			bytes = (headString+"\n").getBytes();
			System.out.println("开始读取响应头");
			output.write(bytes);
			output.flush();
			System.out.println(headString);
			System.out.println("响应头传送完成");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("响应头传送失败，请重新传送");
		}
	}
	
	
	//根据请求作出相应的响应设置
		public HttpResponseBean getHttpResponseByUri(String uri){
			HttpResponseBean responseBean = new HttpResponseBean();
			try {
				//如果请求的文件存在
				File file = new File(Constants.WEB_ROOT, uri);
				if(file.exists()){
//					responseBean.setResponseState("OK");
					responseBean.setResponseState("HTTP/1.1 200 OK");
				}else if(!file.exists()){
					responseBean.setResponseState("HTTP/1.1 404 Not Found");
				}else{
					responseBean.setResponseState("HTTP/1.1 500 Internal Server Error");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//根据请求的文件类型返回响应的响应设置
			if(uri == null){
				return responseBean;
			}
			
			if(uri.endsWith("html")){
				responseBean.setResponseParameter("Content-Type", "text/html; charset=utf-8;");
			}else if(uri.endsWith("jpg")){
				responseBean.setResponseParameter("Content-Type", "image/jpg");
			}else if(uri.endsWith("js")){
				responseBean.setResponseParameter("Content-Type", "text/javascript");
			}else if(uri.endsWith("png")){
				responseBean.setResponseParameter("Content-Type", "image/png");
			}else if(uri.endsWith("css")){
				responseBean.setResponseParameter("Content-Type", "text/css");
			}else if(uri.endsWith("mp3")){
				responseBean.setResponseParameter("Content-Type", "audio/mp3");
			}else{
				responseBean.setResponseParameter("Content-Type", "text/html; charset=utf-8;");
			}
			return responseBean;
		}
}
