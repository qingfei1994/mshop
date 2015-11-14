/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package test;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

/**
 * Description		: 
 * <p/>
 * <br><br>Time		: 2015-11-14 上午9:54:56
 *
 * @author CHQ
 * @version 1.0
 * @since 1.0
 */
public class TestJDBCConnection {
	private static final String DBDriver = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/_activity";
	private static final String DBURL2="jdbc:mysql://127.0.0.1:3306/mshop20150713?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private Connection conn;

	public Connection getConnection() {

		try {
			Class.forName(DBDriver);
			conn = DriverManager.getConnection(DBURL2, USERNAME, PASSWORD);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public PreparedStatement getPrepareStatement(String sql) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}

	public void close(Connection conn) {

		if (conn != null)
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void close(PreparedStatement ps) {
		if (ps != null)
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void close(ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	@Test
	public void test() {
		getConnection();
	}

}
