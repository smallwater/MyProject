package response;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class HttpResponseFacade implements ServletResponse{
	private HttpResponse response;
	
	public HttpResponseFacade(HttpResponse response) {
		this.response = response;
	}
	
	@Override
	public void flushBuffer() throws IOException {
		response.flushBuffer();
	}

	@Override
	public int getBufferSize() {
		return response.getBufferSize();
	}

	@Override
	public String getCharacterEncoding() {
		return response.getCharacterEncoding();
	}

	@Override
	public String getContentType() {
		return response.getContentType();
	}

	@Override
	public Locale getLocale() {
		return response.getLocale();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return response.getOutputStream();
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return response.getWriter();
	}

	@Override
	public boolean isCommitted() {
		return response.isCommitted();
	}

	@Override
	public void reset() {
		response.reset();
	}

	@Override
	public void resetBuffer() {
		response.resetBuffer();
	}

	@Override
	public void setBufferSize(int paramInt) {
		response.setBufferSize(paramInt);
	}

	@Override
	public void setCharacterEncoding(String paramString) {
		response.setCharacterEncoding(paramString);
	}

	@Override
	public void setContentLength(int paramInt) {
		response.setContentLength(paramInt);
	}

	@Override
	public void setContentType(String paramString) {
		response.setContentType(paramString);
	}

	@Override
	public void setLocale(Locale paramLocale) {
		response.setLocale(paramLocale);
	}

}
