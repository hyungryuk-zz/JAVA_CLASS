package refactoring.state;
public class MyRobot {
	private int currentState; // 0 : Happy, 1: Angry, 2: Cold 

	public MyRobot() {
		currentState = 0;
	}
	
	public void setState(int currentState) {
		this.currentState = currentState;
	}	

	public void talk() {
		if (currentState == 0) { 
			System.out.println("����. ����~ ����~ ");
		} else if (currentState == 1) {
			System.out.println("����. �Ⱦ�~ �Ⱦ�~ ");
		} else if (currentState == 2) {
			System.out.println("����. �䷷~ �䷷~ ");
		}
		
	}
}