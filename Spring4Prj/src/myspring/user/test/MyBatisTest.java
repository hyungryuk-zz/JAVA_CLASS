package myspring.user.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import myspring.user.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)	//스프링이 Junit을 확장해서 실행한다는 의미이다
@ContextConfiguration(locations="classpath:config/beans.xml")	//bean을 설정한 xml파일명을 써준다.
public class MyBatisTest {
	
	private String namespace = "config.usersMapper.";
	@Autowired
	SqlSessionFactory factory;
	
	@Autowired
	SqlSession session;
	
	@Test
	public void mybatis() {
		System.out.println(factory.getClass().getName());
		System.out.println(session.getClass().getName());
		//sql문 실행
		UserVO user = session.selectOne(namespace+"getUser","gildong");
		System.out.println(user);	
		
		//System.out.println(session.insert(namespace+"insertUser", new UserVO("ryuk0203", "김형륙", "남", "수원")));
		

		session.update(namespace+"updateUser", new UserVO("ryuk0203","김디디","남","수원"));
		List<UserVO>userList = session.selectList(namespace+"getUserList");
		userList.stream().forEach(System.out::println);
		
	}
}
