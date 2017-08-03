package myspring.user.dao;

import java.util.List;

import myspring.user.vo.UserVO;

public interface UserDAO {
	
	public UserVO getUser(String userId);
	
	public List<UserVO> getUserList();
	
	public int addUser(UserVO user);
	
	public int removeUser(String userId);
}
