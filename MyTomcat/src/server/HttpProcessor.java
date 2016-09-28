package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

import request.HttpRequest;
import request.HttpRequestBean;
import response.HttpResponse;

public class HttpProcessor implements Runnable{
	private InputStream input;
	private OutputStream output;
	private Socket socket;
	public HttpProcessor(Socket socket) {
		try {
			this.input = socket.getInputStream();
			this.output = socket.getOutputStream();
			this.socket = socket;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
		Logger log = Logger.getLogger("ServerLog");
		
		//获取请求并解析
		HttpRequest request = new HttpRequest(input);
		HttpRequestBean rb = request.getRequestBean();
		String uri = rb.getAttribute("uri");
		log.info(rb.getAttribute("method") +" "+uri+" "+rb.getAttribute("protocol"));
		//根据请求作出相应的响应
		//servlet请求
		if(uri != null){
			if(uri.startsWith("/servlet/")){
				//请求uri以/servlet/开头，表示servlet请求
				System.out.println("接收到Servlet请求");
				
//				HttpResponseBean responseBean = new HttpResponseBean();
				HttpResponse response = new HttpResponse(output,uri);
				response.sendHttpResponseHeader();//发送响应头
				ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
			} else {
				System.out.println("接收到URI请求");
//				HttpResponseBean responseBean = getHttpResponseByHttpRequest(uri);
				//发送响应
//				HttpResponse response = new HttpResponse(output, responseBean, uri);
				HttpResponse response = new HttpResponse(output, uri);
				response.sendHttpResponseHeader();//发送响应头
				response.SendStaticFile();//发送webroot目录下的静态文件
			}
		}
		
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*//根据请求作出相应的响应设置
	public HttpResponseBean getHttpResponseByHttpRequest(String uri){
		HttpResponseBean responseBean = new HttpResponseBean();
		try {
			//如果请求的文件存在
			File file = new File(Constants.WEB_ROOT, uri);
			if(file.exists()){
				responseBean.setResponseState("OK");
			}else{
				responseBean.setResponseState("NotFound");
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
	}*/

}
