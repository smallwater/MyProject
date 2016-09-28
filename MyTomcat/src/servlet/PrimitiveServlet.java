package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PrimitiveServlet implements Servlet{

	@Override
	public void destroy() {
		System.out.println("Servlet销毁");
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void init(ServletConfig paramServletConfig) throws ServletException {
		System.out.println("Servlet初始化");
	}

	@Override
	public void service(ServletRequest servletRequest, ServletResponse servletResponse)
			throws ServletException, IOException {
		System.out.println("from service");
        PrintWriter out = servletResponse.getWriter();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>测试文件</title>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<h1>Hello world</h1>");
        out.println("<br>");
        out.println("Violets are <a href=\"../index.html\">blue</a>.");
        
        out.println("</body>");
        out.println("</html>");
	}

}
