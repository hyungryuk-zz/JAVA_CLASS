package chap18.sec07.exam01_serversocket_socket.rw.level3.serial;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExample {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			//ServerSocket생성
			serverSocket = new ServerSocket();		
			serverSocket.bind(new InetSocketAddress("localhost", 5001));
			//serverSocket = new ServerSocket(5001);
			while(true) {
				System.out.println( "Server [연결 기다림]");
				//클라이언트로부터의 요청을 수락하고, 클라이언트와 통신을 담당할
				//Socket 객체를 생성해준다. 
				Socket socket = serverSocket.accept();
				InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
				//접속한 클라이언트의 IP 주소를 알아냄
				System.out.println("Server [연결 수락함] " + isa.getHostString());
				
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				String message = "헬로 클라이언트";
				dos.writeUTF(message);
				dos.flush();
				System.out.println( "[데이터 보내기 성공]");
				
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Student stu = (Student)ois.readObject();
				System.out.println("Client -> Server : [데이터 받기 성공]: " + stu);

				ois.close();
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
