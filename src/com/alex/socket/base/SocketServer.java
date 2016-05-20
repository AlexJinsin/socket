package com.alex.socket.base;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public SocketServer() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			Socket socket = serverSocket.accept();
			Reader reader = new InputStreamReader(socket.getInputStream());

			char buffer[] = new char[10];
			int len;
			StringBuffer stringBuffer = new StringBuffer();
			while ((len = reader.read(buffer)) != -1) {
				
				stringBuffer.append(new String(buffer,0,len));
				
			}
			
			System.out.println("收到  来自client的信息" + stringBuffer);
			
			reader.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
