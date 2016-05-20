package com.alex.socket.evo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
				
				StringBuilder sb = new StringBuilder();
				String temp;
				int index;
				
				while ((temp = bufferedReader.readLine()) != null) {
					if ((index = temp.indexOf("eof")) != -1) {// 遇到eof时就结束接收
						sb.append(temp.substring(0, index));
						break;
					}
					sb.append(temp);
				}
				
				System.out.println("from client: " + sb);
				
				Writer writer = new OutputStreamWriter(socket.getOutputStream(),"UTF-8");
				writer.write("client 请求收到  server 返回成功");
				writer.write("eof\n");
				writer.flush();
				writer.close();
				bufferedReader.close();
				socket.close();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
