package myspring.di.annotation;

import org.springframework.stereotype.Component;

@Component
public interface Printer {
	public void print(String message);
}
