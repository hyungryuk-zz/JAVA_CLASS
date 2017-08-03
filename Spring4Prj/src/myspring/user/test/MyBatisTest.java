package myspring.user.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import myspring.user.vo.EmpVO;
import myspring.user.dao.UserDAO;
import myspring.user.vo.DeptVO;
import myspring.user.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)	//�������� Junit�� Ȯ���ؼ� �����Ѵٴ� �ǹ��̴�
@ContextConfiguration(locations="classpath:config/beans.xml")	//bean�� ������ xml���ϸ��� ���ش�.
public class MyBatisTest {
	
	private String namespace = "config.usersMapper.";
	@Autowired
	SqlSessionFactory factory;
	
	@Autowired
	SqlSession session;
	
	@Autowired
	UserDAO userDAO;
	
	 @Test
	   public void dao() {
	      UserVO user = userDAO.getUser("gildong");
	      System.out.println(user);
	      
	      int cnt = userDAO.removeUser("gildong");
	      System.out.println("���� �Ǽ� : " + cnt);
	      
	      System.out.println(userDAO.getUserList());
	   }

	
	@Test @Ignore
	public void insertEmp() {
		EmpVO empvo = new EmpVO("���","PROG",7788,"20170802" ,9000.0F,0.3F,new DeptVO(30));
		int cnt = session.update("config.EMpDeptMap.insertEmp", empvo);
		System.out.println(cnt);
	}
	@Test @Ignore
	public void emp() {
		List<EmpVO>list = 
				session.selectList("config.EMpDeptMap.selectEmpDeptByJob","MAN");
		list.stream().forEach(System.out::println);			
		System.out.println("-------------------------------");
		EmpVO vo = new EmpVO();
		vo.setEmpHireDate("1987");
		vo.setEmpSal(5000.0F);
		list = session.selectList("config.EMpDeptMap.selectByHireDateorSal", vo);
		list.stream().forEach(System.out::println);			
	}
	@Test @Ignore
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
