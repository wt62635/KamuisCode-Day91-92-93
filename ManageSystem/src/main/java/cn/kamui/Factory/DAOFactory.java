package cn.kamui.Factory;

import cn.kamui.DAO.IUserDAO;
import cn.kamui.DAO.IUserDAOImpl;
/**
 * �����������ݿ��������
 * @author ���֮��
 *
 */
public class DAOFactory {
	public static IUserDAO getIUserDAOInstance() {
		return new IUserDAOImpl();
	}
}
