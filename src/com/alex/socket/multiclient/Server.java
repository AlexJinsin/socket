package com.alex.socket.multiclient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public Server() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		
		
		try {
			int listenPort = 8888;	
			ServerSocket serverSocket = new ServerSocket(listenPort);
//			阻塞
			while(true) {
				//每次都阻塞 socket
				Socket socket = serverSocket.accept();
				
				Reader reader = new InputStreamReader(socket.getInputStream());
				
				char chars[] = new char[64];
				int len;
				StringBuilder sb = new StringBuilder();
				String temp;
				int index;
				while ((len = reader.read(chars)) != -1) {
					temp = new String(chars, 0, len);
					if ((index = temp.indexOf("eof")) != -1) {// 遇到eof时就结束接收
						sb.append(temp.substring(0, index));
						break;
					}
					sb.append(temp);
				}
				System.out.println("from client: " + sb);
				
				Writer writer = new OutputStreamWriter(socket.getOutputStream());
				writer.write("client 请求收到  server 返回成功");
				writer.flush();
				writer.close();
				reader.close();
				socket.close();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
