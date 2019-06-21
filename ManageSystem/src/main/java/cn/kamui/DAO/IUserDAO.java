package cn.kamui.DAO;

import java.util.List;

import cn.kamui.User.User;
/**
 * 数据库操作功能接口
 * @author 曙光之刃
 *
 */
public interface IUserDAO {
	/**
	 * 数据库创建操作
	 */
	public boolean doCreate(User user) throws Exception;
	/**
	 * 数据库删除操作,按id删除
	 */
	public boolean doDelete(int id) throws Exception;
	/**
	 * 数据库更改操作
	 */
	public boolean doUpdate(User user) throws Exception;
	/**
	 * 数据库通过ID查找数据操作
	 */
	public User findById(int id) throws Exception;
	/**
	 * 数据库查找一组数据操作
	 */
	public List<User> findAll(String keyWord) throws Exception;
}
