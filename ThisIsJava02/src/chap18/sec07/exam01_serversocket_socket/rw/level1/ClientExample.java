package chap18.sec07.exam01_serversocket_socket.rw.level1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientExample {
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket();
			System.out.println( "Client [���� ��û]");
			socket.connect(new InetSocketAddress("localhost", 5001));
			System.out.println( "Client [���� ����]");
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			System.out.println("[������ �ޱ� ����]: " + dis.readUTF());
			
			dis.close();
		} catch(Exception e) {}
		
		if(!socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e1) {}
		}	
	}
}