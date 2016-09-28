package server;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import request.HttpRequest;
import request.HttpRquestFacade;
import response.HttpResponse;
import response.HttpResponseFacade;
import util.Constants;

public class ServletProcessor {
	public void process(HttpRequest request, HttpResponse response) {

		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);
		// 类加载器，用于从指定JAR文件或目录加载类
		URLClassLoader loader = null;

		try {
			URLStreamHandler streamHandler = null;
			// 创建类加载器
			loader = new URLClassLoader(new URL[] { new URL(null, "file:" + Constants.WEB_ROOT, streamHandler) });
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}

		Class<?> myClass = null;
		try {
			// 加载对应的servlet类,参数必须是类的二进制名称，即全限定名称，要加上包名
			myClass = loader.loadClass("servlet." + servletName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}

		Servlet servlet = null;

//		 给request、response增加外观类，安全性考虑，防止用户在servlet里直接将ServletRequest、ServletResponse向下转型为Request和Response类型，
//		 并直接调用其内部的public方法，因为RequestFacade、ResponseFacade里不会有parse、sendStaticResource等方法；
		HttpRquestFacade requestFacade = new HttpRquestFacade(request);
		HttpResponseFacade responseFacade = new HttpResponseFacade(response);
		 try {
		 servlet = (Servlet) myClass.newInstance();
		 servlet.service((ServletRequest)requestFacade,(ServletResponse)responseFacade);
		 } catch (Exception e) {
			 System.out.println(e.toString());
			 e.printStackTrace();
		 } catch (Throwable e) {
			 System.out.println(e.toString());
			 e.printStackTrace();
		 }

	}
}
