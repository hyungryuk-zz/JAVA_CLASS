package myspring.di.xml.test;


import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import myspring.di.xml.Hello;

@RunWith(SpringJUnit4ClassRunner.class)	//�������� Junit�� Ȯ���ؼ� �����Ѵٴ� �ǹ��̴�
@ContextConfiguration(locations="classpath:config/beans.xml")	//bean�� ������ xml���ϸ��� ���ش�.
public class HelloBeanSpringTest {
	
	@Autowired	//autowired������ �ϸ� factory.getBean("");���� ��ü�� ���Խ�ų �ʿ���� �ڵ����� ���Եȴ�
	ApplicationContext factory;
	
	@Autowired
	Hello hello;
	
	
	@Autowired
	Hello helloC;
	
	@Autowired
	DataSource dataSource;
	
	@Test
	public void connection() {
		
		try {
			Connection con = dataSource.getConnection();
			System.out.println(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test @Ignore
	public void setCollection() {
		hello.getNamesSet().stream().forEach(System.out::println);
	}
	@Test @Ignore
	public void collection() {
		hello.getNames().stream().forEach(System.out::println);
	}
	
	
	@Test @Ignore
	public void constructor() {
		Assert.assertEquals("Hello ������", helloC.sayHello());
		System.out.println(helloC.getPrinter().getClass().getName());
	}
	
	
	@Test @Ignore
	public void bean1() {
		//System.out.println(factory.getClass().getName());
		//Hello hello = factory.getBean("hello",Hello.class);
		Assert.assertEquals("Hello �浿��", hello.sayHello());
	}
	
	
}
