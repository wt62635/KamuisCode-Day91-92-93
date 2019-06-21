package cn.kamui.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.kamui.User.User;
import cn.kamui.jdbc.DataBaseConnection;
/**
 * 数据库操作功能接口实现
 * @author 曙光之刃
 *
 */
public class IUserDAOImpl implements IUserDAO {
	private DataBaseConnection dc;
	private Connection conn = null ;
	//通过构造方法实现数据库连接
	public IUserDAOImpl() {
		this.dc = new DataBaseConnection();
		try {
			this.conn = this.dc.getConn();
		} catch (SQLException e) {
			e.printStackTrace();
			this.dc.close();
		}
	}

	/**
	 * 创建操作实现
	 */
	@Override
	public boolean doCreate(User user) throws Exception {
		boolean flag = false;
		String sql = "INSERT INTO user(name,age,gender,birthday) VALUES(?,?,?,?)";
		//创建事务
		try (PreparedStatement pstm = this.conn.prepareStatement(sql)){			
			pstm.setString(1, user.getName());
			pstm.setInt(2, user.getAge());
			pstm.setString(3, user.getGender());
			pstm.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
			if(pstm.executeUpdate()>0) {//至少更新一行
				flag = true ;//更新成功
			}
		} catch (Exception e) {
			throw e;
		} finally {//关闭数据库
			this.dc.close();
		}
		return flag;
	}

	/**
	 * 删除操作实现
	 */
	@Override
	public boolean doDelete(int id) throws Exception {
		boolean flag = false;
		String sql = "DELETE FROM user WHERE id=?";
		//创建事务
		try (PreparedStatement pstm = this.conn.prepareStatement(sql)){
			pstm.setInt(1, id);
			if(pstm.executeUpdate()>0) {//至少更新一行
				flag = true ;//更新成功
			}
		} catch (Exception e) {
			throw e;
		} finally {//关闭数据库
			this.dc.close();
		}
		return flag;
	}

	/**
	 * 更改操作实现
	 */
	@Override
	public boolean doUpdate(User user) throws Exception {
		boolean flag = false;
		String sql = "UPDATE user SET name=?,age=?,gender=?,birthday=? WHERE id=?";
		//创建事务
		try (PreparedStatement pstm = this.conn.prepareStatement(sql)){			
			pstm.setString(1, user.getName());
			pstm.setInt(2, user.getAge());
			pstm.setString(3, user.getGender());
			pstm.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
			pstm.setInt(5, user.getId());
			if(pstm.executeUpdate()>0) {//至少更新一行
				flag = true ;//更新成功
			}
		} catch (Exception e) {
			throw e;
		} finally {//关闭数据库
			this.dc.close();
		}
		return flag;
	}

	/**
	 * 查询ID数据操作实现
	 */
	@Override
	public User findById(int id) throws Exception {
		User user = null ;
		String sql = "SELECT id,name,age,gender,birthday FROM user WHERE id=?";
		//创建事务
		try (PreparedStatement pstm = this.conn.prepareStatement(sql)){
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();//执行查询操作
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setAge(rs.getInt(3));
				user.setGender(rs.getString(4));
				user.setBirthday(rs.getDate(5));
			}
			rs.close();
		} catch (Exception e) {
			throw e;
		} finally {//关闭数据库
			this.dc.close();
		}
		return user;
	}

	/**
	 * 查询组数据操作实现
	 */
	@Override
	public List<User> findAll(String keyWord) throws Exception {
		List<User> all = new ArrayList<>();
		String sql = "SELECT id,name,age,gender,birthday FROM user WHERE name LIKE ? OR gender LIKE ? OR birthday LIKE ?";
		//创建事务
		try (PreparedStatement pstm = this.conn.prepareStatement(sql)){
			pstm.setString(1, "%"+ keyWord +"%");
			pstm.setString(2, "%"+ keyWord +"%");
			pstm.setString(3, "%"+ keyWord +"%");
			ResultSet rs = pstm.executeQuery();//执行查询操作
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setAge(rs.getInt(3));
				user.setGender(rs.getString(4));
				user.setBirthday(rs.getDate(5));
				all.add(user);
			}
			rs.close();
		} catch (Exception e) {
			throw e;
		} finally {//关闭数据库
			this.dc.close();
		}
		return all;
	}

}
