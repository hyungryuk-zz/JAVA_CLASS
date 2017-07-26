/*
* 애완동물 장난감 로봇은 자신의 감정상태에 따라 주인에게 각기 다른 말을 한다. 
* 세가지 감정상태가 있다.
* 즐거운 상태 / 화난 상태 / 추운 상태
*/
package refactoring.state;

import java.io.*;

/* Replace Type Code with State/Strategy */
public class StateDemoBefore {
	public static void main(String[] args) throws IOException {
		if ( args.length != 1) {
			System.out.println("사용법 : java StateDemoBefore [Happy|Angry|Cold]");
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

