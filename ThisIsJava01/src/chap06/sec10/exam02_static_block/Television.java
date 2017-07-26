package chap06.sec10.exam02_static_block;

public class Television {
	static String company = "Samsung";
	static String model = "LCD";
	static String info;
	int size = 20;
	
	static {
		info = company + "-" + model;
	}
}
