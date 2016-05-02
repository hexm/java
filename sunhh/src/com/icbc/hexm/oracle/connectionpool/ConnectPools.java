package com.icbc.hexm.oracle.connectionpool;

/** 
 * Title: ConnectPool.java 
 * Description: ���ӳع����� 
 * Copyright: Copyright (c) 2002/12/25 
 * Company: 
 * Author : 
 * Version 2.0 
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import com.icbc.hexm.util.Log4jUtil;
import com.icbc.hexm.util.MyUtil;

/**
 * ������DBConnectionManager֧�ֶ�һ�������������ļ���������ݿ�����
 * �صķ���.�ͻ�������Ե���getInstance()�������ʱ����Ψһʵ��.
 */
public class ConnectPools {
	private static final String ORACLE_PASSWORD = "vaim1507";
	private static final String ORACLE_USER = "VAIM1507";
	private static final String ORACLE_CONN = "jdbc:oracle:thin:@122.16.125.221:1521:VACPKF";
	private static final String ORACLE_MAXCONN = "10";
	private static final String ORACLE_JDBC_DRIVER_ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECT_POOL_LOG_FILE = "c:/DBConnectPool-log.txt";
	static public ConnectPools instance; // Ψһʵ��
	static public int clients;
	public Vector<Driver> drivers = new Vector<Driver>(); // ����
	//public PrintWriter log;
	public Hashtable<String, DBConnectionPool> pools = new Hashtable<String, DBConnectionPool>(); // ����

	/**
	 * ����Ψһʵ��.����ǵ�һ�ε��ô˷���,�򴴽�ʵ��
	 * 
	 * @return DBConnectionManager Ψһʵ��
	 */
	static synchronized public ConnectPools getInstance() {
		if (instance == null) {
			instance = new ConnectPools();
		}

		clients++;

		return instance;
	}

	/**
	 * ��������˽���Է�ֹ�������󴴽�����ʵ��
	 */
	private ConnectPools() {
		init();
	}

	/**
	 * �����Ӷ��󷵻ظ�������ָ�������ӳ�
	 * 
	 * @param name
	 *            �������ļ��ж�������ӳ�����
	 * @param con
	 *            ���Ӷ���
	 */
	public void freeConnection(String name, Connection con) {
		DBConnectionPool pool = pools.get(name);
		if (pool != null) {
			pool.freeConnection(con);
		} else {
			System.out.println("pool ==null");
		}
		clients--;
	}

	/**
	 * ���һ�����õ�(���е�)����.���û�п�������,������������С����������� ����,�򴴽�������������
	 * 
	 * @param name
	 *            �������ļ��ж�������ӳ�����
	 * @return Connection �������ӻ�null
	 * @throws SQLException 
	 */
	public Connection getConnection(String name) throws SQLException {
		DBConnectionPool pool = pools.get(name);
		if (pool != null) {
			// return pool.getConnection();
			return pool.returnConnection();
		}
		return null;
	}

	/**
	 * ���һ����������.��û�п�������,������������С���������������, �򴴽�������������.����,��ָ����ʱ���ڵȴ������߳��ͷ�����.
	 * 
	 * @param name
	 *            ���ӳ�����
	 * @param time
	 *            �Ժ���Ƶĵȴ�ʱ��
	 * @return Connection �������ӻ�null
	 * @throws SQLException 
	 */
	public Connection getConnection(String name, long time) throws SQLException {
		DBConnectionPool pool = pools.get(name);
		if (pool != null) {
			return pool.getConnection(time);
		}
		return null;
	}

	/**
	 * �ر���������,�������������ע��
	 */
	public synchronized void release() {
		// �ȴ�ֱ�����һ���ͻ��������
		if (--clients != 0) {
			return;
		}

		Enumeration<DBConnectionPool> allPools = pools.elements();
		while (allPools.hasMoreElements()) {
			DBConnectionPool pool = allPools.nextElement();
			pool.release();
		}
		Enumeration<Driver> allDrivers = drivers.elements();
		while (allDrivers.hasMoreElements()) {
			Driver driver = allDrivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);

				log("����JDBC�������� " + driver.getClass().getName() + "��ע��");
			} catch (SQLException e) {
				log(e, "�޷���������JDBC���������ע��: " + driver.getClass().getName());
			}
		}
	}

	/**
	 * ����ָ�����Դ������ӳ�ʵ��.�˴��ɴ���������ӳ�
	 * 
	 * @param props
	 *            ���ӳ�����
	 */
	private void createPools(Properties props) {
		Enumeration propNames = props.propertyNames();
		while (propNames.hasMoreElements()) {
			String name = (String) propNames.nextElement();
			if (name.endsWith(".url")) {
				String poolName = name.substring(0, name.lastIndexOf("."));
				String url = props.getProperty(poolName + ".url");
				if (url == null) {
					log("û��Ϊ���ӳ�" + poolName + "ָ��URL");
					continue;
				}
				String user = props.getProperty(poolName + ".user");
				String password = props.getProperty(poolName + ".password");
				String maxconn = props.getProperty(poolName + ".maxconn", "0");
				int max;
				try {
					max = Integer.valueOf(maxconn).intValue();
				} catch (NumberFormatException e) {
					log("������������������: " + maxconn + " .���ӳ�: " + poolName);
					max = 0;
				}
				DBConnectionPool pool = new DBConnectionPool(poolName, url, user, password, max);
				pools.put(poolName, pool);
				log("�ɹ��������ӳ�" + poolName);
			}
		}
	}

	/**
	 * ��ȡ������ɳ�ʼ��
	 */
	private void init() {
		try {
			Properties dbProps = new Properties();
			String configs = System.getProperty("user.dir") + "/conf/db.properties";
			System.out.println("configs file local at " + configs);
			//FileInputStream is = new FileInputStream(configs);
			try {
				//dbProps.load(is);
				//dbProps.put("logfile", DB_CONNECT_POOL_LOG_FILE);
				dbProps.put("drivers", ORACLE_JDBC_DRIVER_ORACLE_DRIVER);
				dbProps.put("oracle.maxconn", ORACLE_MAXCONN);
				dbProps.put("oracle.url", ORACLE_CONN);
				dbProps.put("oracle.user", ORACLE_USER);
				dbProps.put("oracle.password", ORACLE_PASSWORD);
			} catch (Exception e) {
				System.err.println("���ܶ�ȡ�����ļ�. " + "��ȷ��db.properties��CLASSPATHָ����·����");
				return;
			}
//			String logFile = dbProps.getProperty("logfile", "DBConnectionManager.log");
//			try {
//
//				log = new PrintWriter(new FileWriter(logFile, true), true);
//			} catch (IOException e) {
//				System.err.println("�޷�����־�ļ�: " + logFile);
//				log = new PrintWriter(System.err);
//			}
			loadDrivers(dbProps);
			createPools(dbProps);
		} catch (Exception e) {
		}
	}

	/**
	 * 171 * װ�غ�ע������JDBC�������� 172 * 173 * @param props ���� 174
	 */
	private void loadDrivers(Properties props) {
		String driverClasses = props.getProperty("drivers");
		StringTokenizer st = new StringTokenizer(driverClasses);
		while (st.hasMoreElements()) {
			String driverClassName = st.nextToken().trim();
			try {
				Driver driver = (Driver) Class.forName(driverClassName).newInstance();
				DriverManager.registerDriver(driver);
				drivers.addElement(driver);
				System.out.println(driverClassName);
				log("�ɹ�ע��JDBC��������" + driverClassName);
			} catch (Exception e) {
				log("�޷�ע��JDBC��������: " + driverClassName + ", ����: " + e);
			}
		}
	}

	/**
	 * ���ı���Ϣд����־�ļ�
	 */
	private void log(String msg) {
		Log4jUtil.getLogger().debug(msg);
	}

	/**
	 * ���ı���Ϣ���쳣д����־�ļ�
	 */
	private void log(Throwable e, String msg) {
		Log4jUtil.getLogger().debug(msg);
		Log4jUtil.getLogger().debug(MyUtil.Execption2String(e));
	}

}
