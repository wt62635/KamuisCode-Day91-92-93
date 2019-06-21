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
		
		// ���������ʾ��Ϣ
		String errorMessage;
		// �ж��û����Ƿ���ȷ
		if ("root".equals(username)) {
			// �û�����ȷ�������ж������Ƿ���ȷ
			if ("1234".equals(password)) {
				// ����Ҳ��ȷ�����¼�ɹ����ض�����ҳ
				return null;
			} else {
				// ���������׼���ô�����ʾ
				errorMessage = "[ModelMap] ��¼ʧ�ܣ��������";
				// ��������ʾ��װ�����������
				modelMap.addAttribute("msg", errorMessage);
				// ִ��ת��
				return "error";
			}
		} else {
			// �û�������
			errorMessage = "[ModelMap] ��¼ʧ�ܣ��û��������ڣ�";
			modelMap.addAttribute("msg", errorMessage);
			return "error";
		}
		
	}
	
}




