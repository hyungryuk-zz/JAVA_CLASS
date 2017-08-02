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

@RunWith(SpringJUnit4ClassRunner.class)	//�������� Junit�� Ȯ���ؼ� �����Ѵٴ� �ǹ��̴�
@ContextConfiguration(locations="classpath:config/beans.xml")	//bean�� ������ xml���ϸ��� ���ش�.
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
		//sql�� ����
		UserVO user = session.selectOne(namespace+"getUser","gildong");
		System.out.println(user);	
		
		//System.out.println(session.insert(namespace+"insertUser", new UserVO("ryuk0203", "������", "��", "����")));
		

		session.update(namespace+"updateUser", new UserVO("ryuk0203","����","��","����"));
		List<UserVO>userList = session.selectList(namespace+"getUserList");
		userList.stream().forEach(System.out::println);
		
	}
}
