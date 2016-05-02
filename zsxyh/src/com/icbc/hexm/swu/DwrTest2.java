package com.icbc.hexm.swu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.icbc.hexm.swu.bean.Student;
import com.icbc.hexm.util.Log4jUtil;
import com.icbc.mysql.util.DBUtils;

public class DwrTest2 {

	public String sayHello(String name) throws SQLException {
		// 可以是访问数据库的复杂代码
//		Student student = null;
//		String sql = "select * from zsxyh_user where user_name='" + name.trim() + "'";
//		Log4jUtil.getLogger().debug("sayHello sql:" + sql);
//		Connection conn = null;
//		Statement statement = null;
//		try {
//			conn = DBUtils.getConnection();
//			statement = conn.createStatement();
//			Log4jUtil.getLogger().debug("sayHello statement:" + statement);
//			ResultSet rs = statement.executeQuery(sql);
//			Log4jUtil.getLogger().debug("sayHello rs:" + rs);
//			while (rs.next()) {
//				student = new Student(rs.getString("user_name"), rs.getString("phone"));
//				break;
//			}
//		} catch (SQLException e) {
//			Log4jUtil.getLogger().debug("findStudentByName exception:", e);
//			throw e;
//		} finally {
//			DBUtils.closeResources(conn, statement, null);
//		}
		StringBuffer testMsg = new StringBuffer();
		Connection conn=DBUtils.getConnection();
		if (!conn.isClosed()) {   
			testMsg.append("Succeeded connecting to the Database!<br/>");   
            Statement statement = conn.createStatement();   
            String sql = "select * from zsxyh_user";   
            ResultSet rs = statement.executeQuery(sql);   
            String msg_content;   
            while (rs.next()) {   
            	msg_content = rs.getString("user_name");   
            	testMsg.append(msg_content + "<br/>");   
            }   
        } 
//		return "Hello World! My phone is: " + student == null ? "null" : student.getPhone();
		return testMsg.toString();
	}

}
