package chap06.sec10.exam02_static_block;

public class TelevisionExample {
	public static void main(String[] args) {
		System.out.println(Television.info);
		
		Television tv = new Television();
		
		System.out.println(tv.size);
		tv = null;
		System.out.println(tv.company);
	}
}

