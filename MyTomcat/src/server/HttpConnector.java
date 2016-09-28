package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import log.MyLogger;
import util.Constants;

public class HttpConnector {

	public static void main(String[] args) {
		//初始化日志配置
		MyLogger myLogger = new MyLogger();
		accept();
	}

	private static void accept() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(Constants.PORT);
			System.out.println("启动服务器~~");
			Logger log = Logger.getLogger("ServerLog");
			log.info("服务器在"+server.getLocalSocketAddress() + "开启" );
			ExecutorService executor = Executors.newFixedThreadPool(100);
			while (true) {
				Socket socket = server.accept();
				// 如果有连接，就开启一个处理线程
				executor.execute(new HttpProcessor(socket));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
