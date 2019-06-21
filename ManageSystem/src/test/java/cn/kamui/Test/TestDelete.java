package cn.kamui.Test;


import cn.kamui.Factory.DAOFactory;
import cn.kamui.User.User;

public class TestDelete {
	public static void main(String[] args) throws Exception {
		User user = new User();
		user.setId(1);
		DAOFactory.getIUserDAOInstance().doDelete(user.getId());
	}
}
