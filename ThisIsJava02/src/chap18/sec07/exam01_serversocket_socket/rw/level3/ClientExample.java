package chap18.sec07.exam01_serversocket_socket.rw.level3;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientExample {
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket();
			System.out.println( "Client [연결 요청]");
			socket.connect(new InetSocketAddress("localhost", 5001));
			System.out.println( "Client [연결 성공]");
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			System.out.println("[데이터 받기 성공]: " + dis.readUTF());
			
			Scanner scan = new Scanner(System.in);
			String msg = scan.nextLine();
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(msg);
			oos.flush();
			System.out.println( "Client -> Server : [데이터 보내기 성공]");
			scan.close();

			dis.close();
			oos.close();
		} catch(Exception e) {}
		
		if(!socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e1) {}
		}	
	}
}