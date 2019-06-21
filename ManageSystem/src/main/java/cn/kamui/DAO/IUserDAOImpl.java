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
 * ���ݿ�������ܽӿ�ʵ��
 * @author ���֮��
 *
 */
public class IUserDAOImpl implements IUserDAO {
	private DataBaseConnection dc;
	private Connection conn = null ;
	//ͨ�����췽��ʵ�����ݿ�����
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
	 * ��������ʵ��
	 */
	@Override
	public boolean doCreate(User user) throws Exception {
		boolean flag = false;
		String sql = "INSERT INTO user(name,age,gender,birthday) VALUES(?,?,?,?)";
		//��������
		try (PreparedStatement pstm = this.conn.prepareStatement(sql)){			
			pstm.setString(1, user.getName());
			pstm.setInt(2, user.getAge());
			pstm.setString(3, user.getGender());
			pstm.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
			if(pstm.executeUpdate()>0) {//���ٸ���һ��
				flag = true ;//���³ɹ�
			}
		} catch (Exception e) {
			throw e;
		} finally {//�ر����ݿ�
			this.dc.close();
		}
		return flag;
	}

	/**
	 * ɾ������ʵ��
	 */
	@Override
	public boolean doDelete(int id) throws Exception {
		boolean flag = false;
		String sql = "DELETE FROM user WHERE id=?";
		//��������
		try (PreparedStatement pstm = this.conn.prepareStatement(sql)){
			pstm.setInt(1, id);
			if(pstm.executeUpdate()>0) {//���ٸ���һ��
				flag = true ;//���³ɹ�
			}
		} catch (Exception e) {
			throw e;
		} finally {//�ر����ݿ�
			this.dc.close();
		}
		return flag;
	}

	/**
	 * ���Ĳ���ʵ��
	 */
	@Override
	public boolean doUpdate(User user) throws Exception {
		boolean flag = false;
		String sql = "UPDATE user SET name=?,age=?,gender=?,birthday=? WHERE id=?";
		//��������
		try (PreparedStatement pstm = this.conn.prepareStatement(sql)){			
			pstm.setString(1, user.getName());
			pstm.setInt(2, user.getAge());
			pstm.setString(3, user.getGender());
			pstm.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
			pstm.setInt(5, user.getId());
			if(pstm.executeUpdate()>0) {//���ٸ���һ��
				flag = true ;//���³ɹ�
			}
		} catch (Exception e) {
			throw e;
		} finally {//�ر����ݿ�
			this.dc.close();
		}
		return flag;
	}

	/**
	 * ��ѯID���ݲ���ʵ��
	 */
	@Override
	public User findById(int id) throws Exception {
		User user = null ;
		String sql = "SELECT id,name,age,gender,birthday FROM user WHERE id=?";
		//��������
		try (PreparedStatement pstm = this.conn.prepareStatement(sql)){
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();//ִ�в�ѯ����
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
		} finally {//�ر����ݿ�
			this.dc.close();
		}
		return user;
	}

	/**
	 * ��ѯ�����ݲ���ʵ��
	 */
	@Override
	public List<User> findAll(String keyWord) throws Exception {
		List<User> all = new ArrayList<>();
		String sql = "SELECT id,name,age,gender,birthday FROM user WHERE name LIKE ? OR gender LIKE ? OR birthday LIKE ?";
		//��������
		try (PreparedStatement pstm = this.conn.prepareStatement(sql)){
			pstm.setString(1, "%"+ keyWord +"%");
			pstm.setString(2, "%"+ keyWord +"%");
			pstm.setString(3, "%"+ keyWord +"%");
			ResultSet rs = pstm.executeQuery();//ִ�в�ѯ����
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
		} finally {//�ر����ݿ�
			this.dc.close();
		}
		return all;
	}

}
