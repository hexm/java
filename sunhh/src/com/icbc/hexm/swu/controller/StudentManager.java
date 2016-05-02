package com.icbc.hexm.swu.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.icbc.hexm.swu.bean.Student;
import com.icbc.hexm.util.Log4jUtil;
import com.icbc.hexm.util.MyUtil;
import com.icbc.mysql.util.DBUtils;

public class StudentManager {

	/**
	 * 查所有用户,不隐藏电话
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Student> getAllStudentsNoHide() throws Exception {
		List<Student> list = new ArrayList<Student>();

		// OraclePoolManager oraclePoolManager = new OraclePoolManager();
		// ResultSet rs = oraclePoolManager.executeQuery(sql);
		// while (rs.next()) {
		// list.add(new Student(rs.getString("user_name"),
		// rs.getString("phone")));
		// // System.out.println(String.valueOf(rs.getInt("table_rowcount")));
		// }
		// rs.close();
		// oraclePoolManager.close();
		String sql = "select * from zsxyh_user order by update_time";
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DBUtils.getConnection();
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String phone = rs.getString("phone");
				list.add(new Student(rs.getString("user_name") + "[" + rs.getString("college") + "]", phone));
			}
		} catch (SQLException e) {
			Log4jUtil.getLogger().debug("getAllStudents exception:", e);
			throw e;
		} finally {
			DBUtils.closeResources(conn, statement, null);
		}
		return list;
	}
	
	/**
	 * 查询所有用户，隐藏电话
	 * @return
	 * @throws Exception
	 */
	public List<Student> getAllStudents() throws Exception {
		List<Student> list = getAllStudentsNoHide();
		for(Student stu : list) {
			String phone = stu.getPhone();
			stu.setPhone(phone.substring(0, 3) + "****" + phone.substring(7));
		}
		return list;
	}

	public Student getOneStudent() {
		return new Student("hexm", "654654");
	}

	public Student getNewStudent() {
		return new Student();
	}

	/**
	 * 增加用户
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public String addStudent(Student student) throws Exception {
		// String time = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
		// try {
		// OraclePoolManager oraclePoolManager = new OraclePoolManager();
		// String updateSql = "INSERT INTO HEXM_USER VALUES('" +
		// student.getName() + "','" + student.getPhone() + "','" + time + "')";
		// int retCode = oraclePoolManager.update(updateSql);
		// if(retCode != 1) {
		// return "报名失败！";
		// }
		// oraclePoolManager.close();
		// } catch (Exception e) {
		// e.printStackTrace();
		// return MyUtil.Execption2String(e);
		// }
		if (findStudentByName(student.getName()) != null) {
			return student.getName() + "已经报名成功，请不要重复报名，谢谢！";
		}
		String insertValues = "'" + student.getName() + "','" + student.getPhone() + "','" + student.getCollege() + "'";
		String sql = "insert into zsxyh_user(user_name,phone,college) values (" + insertValues + ")";
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DBUtils.getConnection();
			if (!conn.isClosed()) {
				statement = conn.createStatement();
				Log4jUtil.getLogger().debug("addStudent sql:" + sql);
				statement.executeUpdate(sql);
			}
		} catch (SQLException e) {
			Log4jUtil.getLogger().debug("addStudent.add exception:", e);
			return MyUtil.Execption2String(e);
		} finally {
			DBUtils.closeResources(conn, statement, null);
		}
		return "ok";
	}

	/**
	 * 按姓名查用户
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Student findStudentByName(String name) throws Exception {
		Student student = null;
		String sql = "select * from zsxyh_user where user_name='" + name.trim() + "'";
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DBUtils.getConnection();
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				student = new Student(rs.getString("user_name"), rs.getString("phone"));
				break;
			}
		} catch (SQLException e) {
			Log4jUtil.getLogger().debug("findStudentByName exception:", e);
			throw e;
		} finally {
			DBUtils.closeResources(conn, statement, null);
		}
		return student;
	}
	
	public static void main(String[] args) {
		String phone = "13988887766";
		System.out.println(phone.substring(0, 3) + "****" + phone.substring(7));
	}
}
