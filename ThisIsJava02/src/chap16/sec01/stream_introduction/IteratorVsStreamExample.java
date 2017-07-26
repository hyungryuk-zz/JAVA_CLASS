package chap16.sec01.stream_introduction;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class IteratorVsStreamExample {
	public static void main(String[] args) {
		
		List<String> list = Arrays.asList("홍길동", "김철수", "이기자");
		
		//Java 7,6,5 : Iterator 이용한 기존 방식
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()) {
			String name = iterator.next();
			System.out.println(name);
		}
				
		//Java8 : Stream을  이용한 방식
		Stream<String> stream = list.stream();
		stream.forEach( name -> System.out.println(name) );
		
	}
}
