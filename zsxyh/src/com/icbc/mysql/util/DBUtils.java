package com.icbc.mysql.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sina.sae.util.SaeUserInfo;

/**
 * 连接数据库的工具类,被定义成不可继承且是私有访问
 */
public final class DBUtils {
	private static String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_swuzsxyh";
	private static String user = SaeUserInfo.getAccessKey();
	private static String psw = SaeUserInfo.getSecretKey();

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private DBUtils() {

	}

	/**
	 * 获取数据库的连接
	 * 
	 * @return conn
	 */
	public static Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, psw);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return conn;
	}

	/**
	 * 释放资源
	 * 
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public static void closeResources(Connection conn1, PreparedStatement pstmt, ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (null != pstmt) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					} finally {
						if (null != conn1) {
							try {
								conn1.close();
							} catch (SQLException e) {
								e.printStackTrace();
								throw new RuntimeException(e);
							}
						}
					}
				}
			}
		}
	}

	public static void closeResources(Connection conn2, Statement statement, ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (null != statement) {
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					} finally {
						if (null != conn2) {
							try {
								conn2.close();
							} catch (SQLException e) {
								e.printStackTrace();
								throw new RuntimeException(e);
							}
						}
					}
				}
			}
		}

	}
}
