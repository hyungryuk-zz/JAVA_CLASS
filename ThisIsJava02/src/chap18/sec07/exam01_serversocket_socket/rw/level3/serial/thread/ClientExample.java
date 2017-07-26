package chap18.sec07.exam01_serversocket_socket.rw.level3.serial.thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientExample {
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket();
			System.out.println("Client [연결 요청]");
			socket.connect(new InetSocketAddress("localhost", 5001));
			System.out.println("Client [연결 성공]");

			DataInputStream dis = new DataInputStream(socket.getInputStream());
			System.out.println("[데이터 받기 성공]: " + dis.readUTF());

			OutputStream os = socket.getOutputStream();

			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							Scanner scan = new Scanner(System.in);
							//int num = scan.nextInt();
							String name = scan.next();
							ObjectOutputStream oos = new ObjectOutputStream(os);
							oos.writeObject(new Student(10, name));
							oos.flush();
							System.out.println("Client -> Server : [데이터 보내기 성공]");
							oos.close();
							scan.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
			thread.start();
			
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e1) {
			}
		}
	}// main

}