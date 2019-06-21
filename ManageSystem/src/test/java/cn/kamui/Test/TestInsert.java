package cn.kamui.Test;

import java.util.Date;

import cn.kamui.Factory.DAOFactory;
import cn.kamui.User.User;

public class TestInsert {
	public static void main(String[] args) throws Exception {
		User user = new User();
		user.setName("Kamui");
		user.setAge(25);
		user.setGender("ÄÐ");
		user.setBirthday(new Date());
		DAOFactory.getIUserDAOInstance().doCreate(user);
	}
}
