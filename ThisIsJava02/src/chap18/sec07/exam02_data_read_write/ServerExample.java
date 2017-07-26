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
				System.out.println( "[���� ��ٸ�]");
				Socket socket = serverSocket.accept();
				InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
				System.out.println("[���� ������] " + isa.getHostName());
				
				//byte[] bytes = null;
				String message = null;
				
				//InputStream is = socket.getInputStream();
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				System.out.println("[������ �ޱ� ����]: " + dis.readUTF());
				
				//OutputStream os = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				message = "��� Ŭ���̾�Ʈ";
				dos.writeUTF(message);
				dos.flush();
				System.out.println( "[������ ������ ����]");
				
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
