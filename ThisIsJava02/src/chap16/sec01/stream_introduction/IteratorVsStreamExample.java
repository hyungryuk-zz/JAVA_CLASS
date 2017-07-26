package chap16.sec01.stream_introduction;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class IteratorVsStreamExample {
	public static void main(String[] args) {
		
		List<String> list = Arrays.asList("ȫ�浿", "��ö��", "�̱���");
		
		//Java 7,6,5 : Iterator �̿��� ���� ���
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()) {
			String name = iterator.next();
			System.out.println(name);
		}
				
		//Java8 : Stream��  �̿��� ���
		Stream<String> stream = list.stream();
		stream.forEach( name -> System.out.println(name) );
		
	}
}
