package cn.kamui.Test;

import java.util.Date;

import cn.kamui.Factory.DAOFactory;
import cn.kamui.User.User;

public class TestUpdate {
	public static void main(String[] args) throws Exception {
		User user = new User();
		user.setName("¿¨ÄÂÒÀ");
		user.setAge(32);
		user.setGender("Î´Öª");
		user.setBirthday(new Date());
		user.setId(1);
		DAOFactory.getIUserDAOInstance().doUpdate(user);
	}
}
