/**
 * 常量类
 */
package util;

import java.io.File;

public class Constants {
	//服务器端口
	public static final int PORT = 8080;
	//html文件路径
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
	//servlet文件路径
	public static final String WEB_SERVLET_ROOT = System.getProperty("user.dir")
			 + File.separator + "target" + File.separator + "classes";
	
	public static int time = 0;
}
