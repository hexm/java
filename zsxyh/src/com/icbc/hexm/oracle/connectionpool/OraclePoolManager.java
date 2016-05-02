package com.icbc.hexm.oracle.connectionpool;

/** 
 * Title: ConnectPool.java 
 * Description: 数据库操作 
 * Copyright: Copyright (c) 2002/12/25 
 * Company: 
 * Author : 
 * remark : 加入指针回滚 
 * Version 2.0 
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.icbc.hexm.util.MyUtil;

public class OraclePoolManager {

	private Statement stmt;
	private Connection con;
	private ResultSet rst;
	
	
	public OraclePoolManager() throws Exception{
		this.getConn();
	}
	
	/**
	 *对象连接初始化
	 * */

	private Connection getConn() throws Exception {
		try {
			con = ConnectPools.getInstance().getConnection("oracle");
		} catch (Exception e) {
			System.err.println("不能创建连接!");
			System.out.println(MyUtil.Execption2String(e));
		}
		return con;
	}

	/**
	 *同以上方法,加入连接空闲等待时间 待用方法
	 * */

	private Connection getConn_t(String name, long time) throws Exception {
		try {
			con = ConnectPools.getInstance().getConnection(name, time);
		} catch (Exception e) {
			System.err.println("不能创建连接!");
		}
		return con;
	}
	
	/**
	 *执行查询方法1
	 * */
	public ResultSet executeQuery(String SqlStr) throws Exception {
		ResultSet result = null;
		try {
			stmt = con.createStatement();
			result = stmt.executeQuery(SqlStr);
			// here add one line by jnma 12.11
			con.commit();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
			throw new Exception("执行查询语句出错" + e.getStackTrace());
			
		}
		return result;
	}

	/**
	 *执行查询方法2
	 * */
	public ResultSet getRst(String SqlStr) throws Exception {
		// ResultSet result = null;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rst = stmt.executeQuery(SqlStr);
			// here add one line by jnma 12.11
			con.commit();
		} catch (java.sql.SQLException e) {
			throw new Exception("执行查询语句出错");
		}
		return rst;
	}

	/**
	 *执行更新
	 * */
	public int update(String SqlStr) throws Exception {
		int result = -1;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			result = stmt.executeUpdate(SqlStr);
			// here add one line by jnma 12.11
			con.commit();
			if (result == 0)
				System.out.println("执行delete,update,insert SQL出错");
		} catch (java.sql.SQLException e) {
			throw e;
		}
		return result;
	}

	/**
	 *执行事务处理
	 * */
	public boolean handleTransaction(Vector SqlArray) throws Exception {
		boolean result = false;
		int ArraySize = SqlArray.size();
		try {
			stmt = con.createStatement();
			con.setAutoCommit(false);
			System.out.println("ArraySize is" + ArraySize);
			for (int i = 0; i < ArraySize; i++) {
				System.out.println(" 开始执行语句" + (String) SqlArray.elementAt(i));
				stmt.executeUpdate((String) SqlArray.elementAt(i));
				System.out.println(" 执行成功");
			}
			con.commit();
			con.setAutoCommit(true);// 必须
			System.out.println("事务执行成功");
			result = true;
		} catch (java.sql.SQLException e) {
			try {
				System.out.println(e.toString());
				System.out.println("数据库操作失败");
				con.rollback();
			} catch (java.sql.SQLException Te) {
				System.err.println("事务出错回滚异常");
			}
		}
		try {
			con.setAutoCommit(true);
		} catch (java.sql.SQLException e) {
			System.err.println("设置自动提交失败");
		}
		return result;
	}

	/**
	 *释放连接
	 * */
	public void close() throws Exception {
		try {
			if (stmt != null)
				stmt.close();
			if (con != null) {
				ConnectPools.getInstance().freeConnection("oracle", con);
				System.out.println(" [c 正在释放一个连接 ] ");
			}
		} catch (java.sql.SQLException e) {
			System.err.println("释放连接出错");
		}
	}
	
	public static void main(String args[]) {
		try {
//			String tabSql = "SELECT t.table_name, substr(c.comments, 0, 200) table_cnname, t.num_rows AS table_rowcount "
//				+ "FROM all_tables t, all_tab_comments c "
//				+ "WHERE t.owner = c.owner "
//				+ "AND t.table_name = c.table_name "
//				+ "AND t.owner = 'VAIM1510'";
			String sql = "select * from hexm_user";
			OraclePoolManager oraclePoolManager = new OraclePoolManager();
			ResultSet rs = oraclePoolManager.executeQuery(sql);
			StringBuffer sb = new StringBuffer();
			while (rs.next()) {
				System.out.println(rs.getString("user_name") );
				System.out.println(rs.getString("phone") );
				System.out.println(rs.getString("update_time") );
//				System.out.println(String.valueOf(rs.getInt("table_rowcount")));
			}
			
			rs.close();
			String updateSql = "INSERT INTO HEXM_USER VALUES('hexm','18666139132','time')";
			int retCode = oraclePoolManager.update(updateSql);
			System.out.println("retCode:" + retCode);
			oraclePoolManager.close();
			
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

}
