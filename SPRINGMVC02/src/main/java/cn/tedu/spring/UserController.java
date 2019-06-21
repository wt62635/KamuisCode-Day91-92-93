package cn.tedu.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("user")
@Controller
public class UserController {

	@RequestMapping(name="hahaha", path={"reg.do", "register.do"})
	public String showRegister() {
		System.out.println("UserController.showRegister()");
		return "reg"; // /WEB-INF/reg.jsp
	}
	
	@RequestMapping("login.do")
	public String showLogin() {
		System.out.println("UserController.showLogin()");
		return "login"; // /WEB-INF/login.jsp
	}
	
	@RequestMapping("handle_register.do")
	public String handleRegister(User user) {
		System.out.println("UserController.handleRegister()");
		System.out.println("\t" + user);
		return "redirect:login.do";
	}
	
	@RequestMapping(path="handle_login.do", method=RequestMethod.POST)
	public String handleLogin(
		@RequestParam(name="uname", required=false, defaultValue="JSD1902") String username, 
		String password, 
		ModelMap modelMap) {
		System.out.println("UserController.handleLogin();");
		System.out.println("\tusername=" + username);
		System.out.println("\tpassword=" + password);
		
		// 定义错误提示信息
		String errorMessage;
		// 判断用户名是否正确
		if ("root".equals(username)) {
			// 用户名正确，继续判断密码是否正确
			if ("1234".equals(password)) {
				// 密码也正确，则登录成功，重定向到主页
				return null;
			} else {
				// 密码错误，先准备好错误提示
				errorMessage = "[ModelMap] 登录失败！密码错误！";
				// 将错误提示封装到请求对象中
				modelMap.addAttribute("msg", errorMessage);
				// 执行转发
				return "error";
			}
		} else {
			// 用户名错误
			errorMessage = "[ModelMap] 登录失败！用户名不存在！";
			modelMap.addAttribute("msg", errorMessage);
			return "error";
		}
		
	}
	
}




