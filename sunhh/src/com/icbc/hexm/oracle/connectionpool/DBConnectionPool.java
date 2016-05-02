package com.icbc.hexm.oracle.connectionpool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import com.icbc.hexm.util.Log4jUtil;
import com.icbc.hexm.util.MyUtil;

/**
 * ���ڲ��ඨ����һ�����ӳ�.���ܹ�����Ҫ�󴴽�������,ֱ��Ԥ������ ��������Ϊֹ.�ڷ������Ӹ��ͻ�����֮ǰ,���ܹ���֤���ӵ���Ч��.
 */

public class DBConnectionPool {
	// private int checkedOut;
	private Vector freeConnections = new Vector();
	private int maxConn;
	private String name;
	private String password;
	private String URL;
	private String user;
	//private PrintWriter log;

	/**
	 * �����µ����ӳ�
	 * 
	 * @param name
	 *            ���ӳ�����
	 * @param URL
	 *            ���ݿ��JDBC URL
	 * @param user
	 *            ���ݿ��ʺ�,�� null
	 * @param password
	 *            ����,�� null
	 * @param maxConn
	 *            �����ӳ������������������
	 * @param log 
	 */
	public DBConnectionPool(String name, String URL, String user, String password, int maxConn) {
		this.name = name;
		this.URL = URL;
		this.user = user;
		this.password = password;
		this.maxConn = maxConn;
		//this.log = log;
	}

	/**
	 * ������ʹ�õ����ӷ��ظ����ӳ�
	 * 
	 * @param con
	 *            �ͻ������ͷŵ�����
	 */
	public synchronized void freeConnection(Connection con) {
		// ��ָ�����Ӽ��뵽����ĩβ
		try {
			if (con.isClosed()) {
				System.out.println("before freeConnection con is closed");
			}
			freeConnections.addElement(con);
			Connection contest = (Connection) freeConnections.lastElement();
			if (contest.isClosed()) {
				System.out.println("after freeConnection contest is closed");
			}
			notifyAll();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	/**
	 * �����ӳػ��һ����������.��û�п��е������ҵ�ǰ������С��������� ������,�򴴽�������.��ԭ���Ǽ�Ϊ���õ����Ӳ�����Ч,�������ɾ��֮,
	 * Ȼ��ݹ�����Լ��Գ����µĿ�������.
	 * @throws SQLException 
	 */
	public synchronized Connection getConnection() throws SQLException {
		Connection con = null;
		if (freeConnections.size() > 0) {
			// ��ȡ�����е�һ����������
			con = (Connection) freeConnections.firstElement();
			freeConnections.removeElementAt(0);
			try {
				if (con.isClosed()) {
					log("�����ӳ�" + name + "ɾ��һ����Ч����");
					System.out.println("�����ӳ�" + name + "ɾ��һ����Ч����");
					// �ݹ�����Լ�,�����ٴλ�ȡ��������
					con = getConnection();
				}
			} catch (SQLException e) {
				log("�����ӳ�" + name + "ɾ��һ����Ч����ʱ����");
				System.out.println("�����ӳ�" + name + "ɾ��һ����Ч���ӳ���");
				// �ݹ�����Լ�,�����ٴλ�ȡ��������
				con = getConnection();
			}
			if (freeConnections.size() > maxConn) {
				System.out.println(" ɾ��һ��������� ");
				releaseOne();
			}
		}

		else if ((maxConn == 0) || (freeConnections.size() < maxConn)) {
			con = newConnection();
		}

		return con;
	}

	public synchronized Connection returnConnection() throws SQLException {
		Connection con = null;
		// �������С���������,����һ��������
		if (freeConnections.size() < maxConn) {
			con = newConnection();
		}
		// ������ô���������ӣ�����һ�����õľ�����
		else if (freeConnections.size() >= maxConn) {

			con = (Connection) freeConnections.firstElement();
			System.out.println(" [a ���ӳؿ��������� ] : " + "[ " + freeConnections.size() + " ]");
			freeConnections.removeElementAt(0);
			System.out.println(" [b ���ӳؿ��������� ] : " + "[ " + freeConnections.size() + " ]");
			try {
				if (con.isClosed()) {
					log("�����ӳ�" + name + "ɾ��һ����Ч����");
					System.out.println("�����ӳ�" + name + "ɾ��һ����Ч����");
					returnConnection();
				}
			} catch (SQLException e) {
				log("�����ӳ�" + name + "ɾ��һ����Ч����ʱ����");
				System.out.println("�����ӳ�" + name + "ɾ��һ����Ч���ӳ���");
				returnConnection();
			}
		}
		return con;
	}

	/**
	 * �����ӳػ�ȡ��������.����ָ���ͻ������ܹ��ȴ����ʱ�� �μ�ǰһ��getConnection()����.
	 * 
	 * @param timeout
	 *            �Ժ���Ƶĵȴ�ʱ������
	 * @throws SQLException 
	 */
	public synchronized Connection getConnection(long timeout) throws SQLException {
		long startTime = new Date().getTime();
		Connection con;
		while ((con = getConnection()) == null) {
			try {
				wait(timeout);
			} catch (InterruptedException e) {
			}
			if ((new Date().getTime() - startTime) >= timeout) {
				// wait()���ص�ԭ���ǳ�ʱ
				return null;
			}
		}
		return con;
	}

	/**
	 * �ر���������
	 */
	public synchronized void release() {
		Enumeration allConnections = freeConnections.elements();
		while (allConnections.hasMoreElements()) {
			Connection con = (Connection) allConnections.nextElement();
			try {
				con.close();
				log("�ر����ӳ�" + name + "�е�һ������");
			} catch (SQLException e) {
				log(e, "�޷��ر����ӳ�" + name + "�е�����");
			}
		}
		freeConnections.removeAllElements();
	}

	/**
	 * �ر�һ������
	 */
	public synchronized void releaseOne() {
		if (freeConnections.firstElement() != null) {
			Connection con = (Connection) freeConnections.firstElement();
			try {
				con.close();
				System.out.println("�ر����ӳ�" + name + "�е�һ������");
				log("�ر����ӳ�" + name + "�е�һ������");
			} catch (SQLException e) {

				System.out.println("�޷��ر����ӳ�" + name + "�е�һ������");
				log(e, "�޷��ر����ӳ�" + name + "�е�����");
			}
		} else {
			System.out.println("releaseOne() bug.......................................................");

		}
	}

	/**
	 * �����µ�����
	 * @throws SQLException 
	 */
	private Connection newConnection() throws SQLException {
		Connection con = null;
		try {
			if (user == null) {
				con = DriverManager.getConnection(URL);
			} else {
				con = DriverManager.getConnection(URL, user, password);
			}
			log("���ӳ�" + name + "����һ���µ�����");

		} catch (SQLException e) {
			log(e, "�޷���������URL������: " + URL);
			throw e;
		}
		return con;
	}
	
	/**
	 * ���ı���Ϣд����־�ļ�
	 */
	private void log(String msg) {
		//log.println(new Date() + ": " + msg);
		Log4jUtil.getLogger().debug(msg);
	}

	/**
	 * ���ı���Ϣ���쳣д����־�ļ�
	 */
	private void log(Throwable e, String msg) {
//		log.println(new Date() + ": " + msg);
//		e.printStackTrace(log);
		Log4jUtil.getLogger().debug(msg);
		Log4jUtil.getLogger().debug(MyUtil.Execption2String(e));
	}
}
