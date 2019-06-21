package cn.kamui.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
/**
 * 数据库连接工具
 * @author 曙光之刃
 *
 */
public class DataBaseConnection {
	private static BasicDataSource ds = null;
	private static Connection conn = null;
	static {
		//创建数据源对象
		ds = new BasicDataSource();
		//读取配置文件
		Properties properties = new Properties();
		try (InputStream readProperties = DataBaseConnection.class.getClassLoader().getResourceAsStream("jdbc.properties"))
		{
			properties.load(readProperties);
			String url = properties.getProperty("url");
			String driver = properties.getProperty("driver");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			//设置连接信息
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			//设置连接数
//			ds.setInitialSize(3);//初始
//			ds.setMaxActive(5);//最大
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库连接对象
	 * @return
	 * @throws SQLException
	 */
	public Connection getConn() throws SQLException{
		conn = ds.getConnection();
		System.out.println(conn);
		return conn;
	}
	/**
	 * 数据库连接关闭操作
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
