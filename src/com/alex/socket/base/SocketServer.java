package com.alex.socket.base;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public SocketServer() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		System.out.println("server");
		try {
//			创建一个socketserver 监听服务端口 8888
			int listenPort = 8888;
			ServerSocket serverSocket = new ServerSocket(listenPort);
//			接受socket连接 阻塞
			Socket socket = serverSocket.accept();
//			接受client的输入
			Reader reader = new InputStreamReader(socket.getInputStream());
			Writer writer = new OutputStreamWriter(socket.getOutputStream());
//			读取socket client发送的信息
			int len;
			char buffer[] = new char[10];
			String temp;
			int index;  
			StringBuffer stringBuffer = new StringBuffer();
//			流在读取的过程中采用阻塞方式有内容读取没内容 就在这死等
			while ((len = reader.read(buffer)) != -1) {
				System.out.println("1");
				 temp = new String(buffer, 0, len);  
//				 如果这里不加eof标识   socket会在这里一直读等到文件都读完 
		         if ((index = temp.indexOf("eof")) != -1) {//遇到eof时就结束接收  
		        	 stringBuffer.append(temp.substring(0, index));  
		            break;  
		         }  
				stringBuffer.append(new String(buffer,0,len));
			}
			System.out.println("收到  来自client的信息" + stringBuffer);
//			写入返回给客户端的内容 
			writer.write("server 返回");
			writer.flush();
//			关闭资源 防止资源泄漏
			writer.close();
			reader.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
