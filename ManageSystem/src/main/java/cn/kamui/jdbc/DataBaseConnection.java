package cn.kamui.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
/**
 * ���ݿ����ӹ���
 * @author ���֮��
 *
 */
public class DataBaseConnection {
	private static BasicDataSource ds = null;
	private static Connection conn = null;
	static {
		//��������Դ����
		ds = new BasicDataSource();
		//��ȡ�����ļ�
		Properties properties = new Properties();
		try (InputStream readProperties = DataBaseConnection.class.getClassLoader().getResourceAsStream("jdbc.properties"))
		{
			properties.load(readProperties);
			String url = properties.getProperty("url");
			String driver = properties.getProperty("driver");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			//����������Ϣ
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			//����������
//			ds.setInitialSize(3);//��ʼ
//			ds.setMaxActive(5);//���
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ���ݿ����Ӷ���
	 * @return
	 * @throws SQLException
	 */
	public Connection getConn() throws SQLException{
		conn = ds.getConnection();
		System.out.println(conn);
		return conn;
	}
	/**
	 * ���ݿ����ӹرղ���
	 */
	public void close() {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
