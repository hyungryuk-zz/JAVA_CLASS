package myspring.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import myspring.user.dao.UserDAO;
import myspring.user.vo.UserVO;

@Controller
public class userContoroller {

	@Autowired
	UserDAO userDAO;

	// @RequestMapping("/hello.do")
	// public String sayHello(@RequestParam String name, Model model) {
	// model.addAttribute("greet", "hello" + name); //Model�� setAttribute�� ���� ������ �ϱ�
	// ���� ���ȴ�. Key�� greet�̰� value�� "hello"+name�̴�
	// return "hello.jsp";
	//
	// }

	@RequestMapping("/hello.do")
	public ModelAndView sayHello(@RequestParam String name) {
		return new ModelAndView("hello", "greet", "Hello " + name);

	}

	@RequestMapping("/getUserList.do")
	public ModelAndView getUserList() {
		List<UserVO> userList = userDAO.getUserList();
		return new ModelAndView("userList", "userListKey", userList);
	}

	@RequestMapping("/getUserInfo.do")
	public ModelAndView getUser(@RequestParam String id) {
		UserVO user = userDAO.getUser(id);
		return new ModelAndView("user", "userKey", user);
	}

	@RequestMapping("/addUserForm.do")
	public String addUserForm(Model model) {
		List<String> genderList = new ArrayList<>();
		genderList.add("��");
		genderList.add("��");

		List<String> cityList = new ArrayList<>();
		cityList.add("����");
		cityList.add("���");
		cityList.add("����");
		cityList.add("�뱸");
		cityList.add("�λ�");
		cityList.add("���");
		cityList.add("����");
		cityList.add("����");
		cityList.add("����");
		cityList.add("����");
		cityList.add("������ �Ǽ��� �Ǽ���");

		Map<String, List<String>> dataMap = new HashMap<>();
		dataMap.put("gender", genderList);
		dataMap.put("city", cityList);

		model.addAttribute("dataKey", dataMap);
		return "userAdd";
	}

	@RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
	public String addUser(@ModelAttribute UserVO user) {

		if (user != null)
			userDAO.addUser(user);
		return "redirect:/getUserList.do";

	}

	@RequestMapping("/removeUser.do/{userid}")
	public String removeUser(@PathVariable String userid) {
		int cnt = userDAO.removeUser(userid);
		if(cnt==1) {
			return "redirect:/getUserList.do";
		}else
			return "";
	}

}
