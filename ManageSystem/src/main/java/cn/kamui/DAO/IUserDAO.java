package cn.kamui.DAO;

import java.util.List;

import cn.kamui.User.User;
/**
 * ���ݿ�������ܽӿ�
 * @author ���֮��
 *
 */
public interface IUserDAO {
	/**
	 * ���ݿⴴ������
	 */
	public boolean doCreate(User user) throws Exception;
	/**
	 * ���ݿ�ɾ������,��idɾ��
	 */
	public boolean doDelete(int id) throws Exception;
	/**
	 * ���ݿ���Ĳ���
	 */
	public boolean doUpdate(User user) throws Exception;
	/**
	 * ���ݿ�ͨ��ID�������ݲ���
	 */
	public User findById(int id) throws Exception;
	/**
	 * ���ݿ����һ�����ݲ���
	 */
	public List<User> findAll(String keyWord) throws Exception;
}
