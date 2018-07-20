package com.baidu.mybaidu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.baidu.mybaidu.dto.UserForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.mybaidu.pojo.User;
import com.baidu.mybaidu.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	//登录
	@ResponseBody
	@RequestMapping("/login")
	public Map<String,Object> login(@RequestBody @Valid UserForm userForm, HttpServletRequest request,BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			throw new Exception("用户名或密码不能为空！");
		}
		Map<String,Object> result = new HashMap<>();
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		// 用户已经登录了，无需重复登录,跳转回主页
		if (currentUser != null) {
			result.put("errno","0");
			result.put("msg","success");
			return result;
		}

		User user = new User();
		user.setUserName(userForm.getUserName());
		user.setPassword(userForm.getPassword());
		currentUser = userService.login(user);

		if(currentUser == null){
			result.put("errno","-1");
			result.put("msg","用户名或密码错误");
			return result;
		}else{
			session.setAttribute("currentUser",currentUser);
			logger.fatal("登录成功");
			result.put("errno","0");
			result.put("msg","success");
			return result;
		}
	}
	@RequestMapping(value = "/forward",method = RequestMethod.GET)
	public String forward(){
		logger.fatal("跳转");
		return "main/home";
	}
	//退出
	@RequestMapping("/logout")
	public String logout(HttpSession session){

		session.invalidate();

		return "redirect:/login.jsp";

	}

	//首页
	@RequestMapping(value ="/home",method = RequestMethod.GET)
	public String home(){
		return "main/home";
	}

	//切换到list.jsp
	@RequestMapping(value="/pageUser",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String pageDepartment(HttpServletRequest request){

		return "user/list";
	}

	//查询所有用户
	@ResponseBody
	@RequestMapping(value="/queryAllUser",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	//page传入页码
	public Map<String,Object> queryAllDepartment(@RequestParam("pageNo")Integer pageNo,@RequestParam("pageSize")Integer pageSize){
		Map<String,Object> map  = userService.queryAllUser(pageNo,pageSize);
		return map;
	}

	//添加角色
	@ResponseBody
	@RequestMapping(value="/createUser",method = RequestMethod.POST)
	public String createUser(HttpServletRequest request){
		String name = request.getParameter("userName");
		String trueName = request.getParameter("trueName");
		String password = request.getParameter("password");
		User user = new User();
		user.setRoleName("用户");
		user.setPassword(password);
		user.setUserName(name);
		user.setTrueName(trueName);

		String url = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath();

		// 检查用户是否已经注册过啦
		User currentUser = userService.signUp(user);

		if (currentUser != null) {
			return "This name is taken  Or You've already signed up<br><a href = \""+url+"/signup\">Click  Me To Re-Sign-up</a><br>" +
					"<a href = \""+url+"\">Click Me To Login</a>";
		}

		int userNums = userService.countUserNum();
		if (userNums<=100){
			boolean bool = userService.createUser(user);
			if(bool){
				return "Sign up succeed <a href = \""+url+"\">Click  Me To Login</a>";
			}else {
				return "Sign up Failed <a href = \""+url+"/signup\">Click Me To Re-Sign-Up</a>";
			}
		}else {
			return "User Number is full<br>" +
					"Anyway, you can still login with a guest account<br>" +
					"username: guest<br>" +
					"password: guest<br>" +
					"<a href = \"" + url + "\">Click  Me To Login</a>";
		}
	}

	//获取要修改的用户
	@ResponseBody
	@RequestMapping(value="/queryUserById",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String,Object> queryUserById(@RequestParam("id")int id){
		Map<String,Object> map = userService.queryUserById(id);
		return map;
	}

	//更新设备
	@ResponseBody
	@RequestMapping(value="/updateUser",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean updateUser(@RequestBody User user){
		Boolean bool = userService.updateUser(user);
		return bool;
	}

	//删除用户
	@ResponseBody
	@RequestMapping(value="/deleteUser/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean deleteUser(@PathVariable("id") String id){
		Boolean bool = userService.deleteUser(id);
		return bool;
	}

	//切换到list.jsp
	@RequestMapping(value="/pageRole",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String pageRole(HttpServletRequest request){

		return "user/roleList";
	}

	//查询所有角色
	@ResponseBody
	@RequestMapping(value="/queryAllRole",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String,Object> queryAllRole(){
		Map<String,Object> map  = userService.queryAllRole();
		return map;
	}

	//添加角色
	@ResponseBody
	@RequestMapping(value="/createRole",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean createEquipment(@RequestBody Map<String,String> param){
		String createId = param.get("createId");
		String roleName = param.get("roleName");
		String remark = param.get("remark");
		boolean bool = userService.createRole(createId,roleName,remark);
		return bool;
	}

	//删除角色
	@ResponseBody
	@RequestMapping(value="/deleteRole/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean deleteRole(@PathVariable("id") String id){
		Boolean bool = userService.deleteRole(id);
		return bool;
	}
}
