package com.alex.socket.base;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class SocketClient {

	public SocketClient() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		try {
			Socket client = new Socket("127.0.0.1", 8888);
			Writer writer = new OutputStreamWriter(client.getOutputStream());
			writer.write("hello world from client");
			writer.flush();
			writer.close();
			client.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
