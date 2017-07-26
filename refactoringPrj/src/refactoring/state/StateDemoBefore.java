/*
* �ֿϵ��� �峭�� �κ��� �ڽ��� �������¿� ���� ���ο��� ���� �ٸ� ���� �Ѵ�. 
* ������ �������°� �ִ�.
* ��ſ� ���� / ȭ�� ���� / �߿� ����
*/
package refactoring.state;

import java.io.*;

/* Replace Type Code with State/Strategy */
public class StateDemoBefore {
	public static void main(String[] args) throws IOException {
		if ( args.length != 1) {
			System.out.println("���� : java StateDemoBefore [Happy|Angry|Cold]");
			System.exit(0);
		}
		
		MyRobot roboto = new MyRobot();
		
		if ( args[0].equals("Happy") )
			roboto.setState(0);
		else if ( args[0].equals("Angry") )
			roboto.setState(1);
		else if ( args[0].equals("Cold"))
			roboto.setState(2);
		
		roboto.talk();	
	}
}

