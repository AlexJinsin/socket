package com.alex.socket.base;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class SocketClient {

	public SocketClient() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		System.out.println("client");
		try {
			
//			创建socket client连接  连接本地ip   端口 8888(端口尽量选大一些因为  小的端口系统保留 建议5位数)
			int port = 8888;
			Socket client = new Socket("127.0.0.1", port);
//			向socket server 写入本次需要发送的内容
			Writer writer = new OutputStreamWriter(client.getOutputStream());

			writer.write("hello world from client");
			writer.write("eof");//发送 结束才能从server中读取内容
			writer.flush();
			
			
			
			Reader reader = new InputStreamReader(client.getInputStream());
			int len;
			char buffer[] = new char[10];
			StringBuffer stringBuffer = new StringBuffer();
			while ((len = reader.read(buffer)) != -1) {
				System.out.println(2);
				stringBuffer.append(new String(buffer,0,len));
			}
			
			System.out.println("client  接到 server的 返回:" + stringBuffer);
			
			reader.close();

			
			
//			关闭资源 （高版本编译器会提示资源泄漏 ）
			writer.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
