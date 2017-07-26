package chap12.sec02.exam01_createthread;

import java.awt.Toolkit;

public class BeepPrintExampleNested {
	public static void main(String[] args) {
		System.out.println("현재 스레드의 이름 " +Thread.currentThread().getName());
		//thread생성 및 시작
		//Thread worker = new MyThread();
		Runnable job = new BeepPrintExampleNested().new MyThreadJob();
		Thread worker = new Thread(job);
		worker.setName("띵띵 스레드");
		worker.start();
		
		Thread worker2 = new BeepPrintExampleNested.MyThread();
		worker2.start();
		
		for(int i=0; i<5; i++) {
			System.out.println("띵");
			try { Thread.sleep(500); } catch(Exception e) {}
		}
	}//main
	
	//static inner class
	static class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println("현재 스레드의 이름 " +Thread.currentThread().getName());
			Toolkit toolkit = Toolkit.getDefaultToolkit();	
			for(int i=0; i<5; i++) {		
				toolkit.beep();
				try { Thread.sleep(500); } catch(Exception e) {}
			}
		}
	}//MyThread
	
	//Instance Inner class
	class MyThreadJob implements Runnable {
		@Override
		public void run() {
			System.out.println("현재 스레드의 이름 " +Thread.currentThread().getName());
			Toolkit toolkit = Toolkit.getDefaultToolkit();	
			for(int i=0; i<5; i++) {		
				toolkit.beep();
				try { Thread.sleep(500); } catch(Exception e) {}
			}
		}
	}//MyThreadJob
}//BeepPrintExampleNested

