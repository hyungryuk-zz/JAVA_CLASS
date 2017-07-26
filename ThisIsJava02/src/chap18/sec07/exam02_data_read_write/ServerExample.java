package chap18.sec07.exam02_data_read_write;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExample {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket();		
			serverSocket.bind(new InetSocketAddress("localhost", 5001));
			while(true) {
				System.out.println( "[연결 기다림]");
				Socket socket = serverSocket.accept();
				InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
				System.out.println("[연결 수락함] " + isa.getHostName());
				
				//byte[] bytes = null;
				String message = null;
				
				//InputStream is = socket.getInputStream();
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				System.out.println("[데이터 받기 성공]: " + dis.readUTF());
				
				//OutputStream os = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				message = "헬로 클라이언트";
				dos.writeUTF(message);
				dos.flush();
				System.out.println( "[데이터 보내기 성공]");
				
				dis.close();
				dos.close();
				socket.close();
			}
		} catch(Exception e) {}
		
		if(!serverSocket.isClosed()) {
			try {
				serverSocket.close();
			} catch (IOException e1) {}
		}
	}
}
