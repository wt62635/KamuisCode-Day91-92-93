package cn.kamui.Factory;

import cn.kamui.DAO.IUserDAO;
import cn.kamui.DAO.IUserDAOImpl;
/**
 * 工厂创建数据库操作对象
 * @author 曙光之刃
 *
 */
public class DAOFactory {
	public static IUserDAO getIUserDAOInstance() {
		return new IUserDAOImpl();
	}
}
