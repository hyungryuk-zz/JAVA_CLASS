package myspring.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import myspring.user.dao.UserDAO;
import myspring.user.vo.UserVO;

@Controller
public class UserRestfulController {

   @Autowired
   UserDAO userDAO;
   
   @RequestMapping(value="/users")
   @ResponseBody
   public List<UserVO> getUserList(){
   
      return userDAO.getUserList();
   }
   
   @GetMapping(value="/users/{userid}")
   @ResponseBody
   public UserVO getUser(String userid) {
      return userDAO.getUser(userid);
   }
   
   @PostMapping(value="/users", headers={"Content-type=application/json"})
   @ResponseBody
   public Integer insertUser(@RequestBody UserVO user) {
      return userDAO.addUser(user);
   }
}