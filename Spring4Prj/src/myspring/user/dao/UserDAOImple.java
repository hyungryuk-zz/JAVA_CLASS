package myspring.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myspring.user.vo.UserVO;

@Repository
public class UserDAOImple implements UserDAO {
	
	@Autowired
	SqlSession session;
	
	
	@Override
	public UserVO getUser(String userId) {
		// TODO Auto-generated method stub
		return session.selectOne("config.usersMapper.getUser",userId);
	}

	@Override
	public List<UserVO> getUserList() {
		// TODO Auto-generated method stub
		return session.selectList("config.usersMapper.getUserList");
	}

	@Override
	public int addUser(UserVO user) {
		// TODO Auto-generated method stub
		return session.insert("config.usersMapper.insertUser",user);
	}

	@Override
	public int removeUser(String userId) {
		// TODO Auto-generated method stub
		return session.delete("config.usersMapper.deleteUser",userId);
	}

}
