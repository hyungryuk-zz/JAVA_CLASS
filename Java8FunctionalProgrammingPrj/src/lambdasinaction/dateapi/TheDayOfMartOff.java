package lambdasinaction.dateapi;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import static java.time.temporal.TemporalAdjusters.*;

import java.time.DayOfWeek;

public class TheDayOfMartOff {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate today = LocalDate.now();
		LocalDate dayofMartoff = today.with(new TemporalAdjuster() {

			@Override
			public Temporal adjustInto(Temporal temporal) {
				//기준날짜 가져오기
				LocalDate theDay = LocalDate.from(temporal);
				System.out.println("기준날짜 : " + theDay);
				//두번째 일요일
				LocalDate secondSun = theDay.with(dayOfWeekInMonth(2, DayOfWeek.SUNDAY));
				System.out.println(secondSun);
				//네번째 일요일
				LocalDate forthSun = theDay.with(dayOfWeekInMonth(4, DayOfWeek.SUNDAY));
				System.out.println(forthSun);
				//기준날짜가 두번째 일요일보다 먼저이면 두번째 일요일
				//기준날짜가 두번째 일요일과 4번째 일요일 사이이면
				//기준날짜가 4번째 일요일보다 뒤에있을 경우
				
				
				
				return null;
			}
		});
		System.out.println("이번달 마트 쉬는날은 : "+dayofMartoff+" 입니다");
	}
	
}
