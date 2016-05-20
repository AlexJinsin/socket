package com.alex.socket.evo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
			client.setSoTimeout(1000*10);
//			向socket server 写入本次需要发送的内容
			Writer writer = new OutputStreamWriter(client.getOutputStream(),"UTF-8");

			writer.write("hello world from client");
			writer.write("eof\n");//发送 结束才能从server中读取内容
			writer.flush();
			
			
			
			BufferedReader bufferReader = new BufferedReader( new InputStreamReader(client.getInputStream(),"UTF-8"));
			
			int index;
			String temp;
			StringBuffer stringBuffer = new StringBuffer();
//			readline 是按照行读取 所以 末尾必须有\n 换行符
			while ((temp = bufferReader.readLine()) != null) {
				
				if ((index = temp.indexOf("eof"))!=-1) {
					stringBuffer.append(temp.substring(0,index));
					break;
				}
				stringBuffer.append(temp);
			}
			
			System.out.println("client  接到 server的 返回:" + stringBuffer);
			
			bufferReader.close();
			
//			关闭资源 （高版本编译器会提示资源泄漏 ）
			writer.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
