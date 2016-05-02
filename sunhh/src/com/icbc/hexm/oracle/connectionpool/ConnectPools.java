package com.icbc.hexm.oracle.connectionpool;

/** 
 * Title: ConnectPool.java 
 * Description: 连接池管理器 
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
 * 管理类DBConnectionManager支持对一个或多个由属性文件定义的数据库连接
 * 池的访问.客户程序可以调用getInstance()方法访问本类的唯一实例.
 */
public class ConnectPools {
	private static final String ORACLE_PASSWORD = "vaim1507";
	private static final String ORACLE_USER = "VAIM1507";
	private static final String ORACLE_CONN = "jdbc:oracle:thin:@122.16.125.221:1521:VACPKF";
	private static final String ORACLE_MAXCONN = "10";
	private static final String ORACLE_JDBC_DRIVER_ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECT_POOL_LOG_FILE = "c:/DBConnectPool-log.txt";
	static public ConnectPools instance; // 唯一实例
	static public int clients;
	public Vector<Driver> drivers = new Vector<Driver>(); // 驱动
	//public PrintWriter log;
	public Hashtable<String, DBConnectionPool> pools = new Hashtable<String, DBConnectionPool>(); // 连接

	/**
	 * 返回唯一实例.如果是第一次调用此方法,则创建实例
	 * 
	 * @return DBConnectionManager 唯一实例
	 */
	static synchronized public ConnectPools getInstance() {
		if (instance == null) {
			instance = new ConnectPools();
		}

		clients++;

		return instance;
	}

	/**
	 * 建构函数私有以防止其它对象创建本类实例
	 */
	private ConnectPools() {
		init();
	}

	/**
	 * 将连接对象返回给由名字指定的连接池
	 * 
	 * @param name
	 *            在属性文件中定义的连接池名字
	 * @param con
	 *            连接对象
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
	 * 获得一个可用的(空闲的)连接.如果没有可用连接,且已有连接数小于最大连接数 限制,则创建并返回新连接
	 * 
	 * @param name
	 *            在属性文件中定义的连接池名字
	 * @return Connection 可用连接或null
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
	 * 获得一个可用连接.若没有可用连接,且已有连接数小于最大连接数限制, 则创建并返回新连接.否则,在指定的时间内等待其它线程释放连接.
	 * 
	 * @param name
	 *            连接池名字
	 * @param time
	 *            以毫秒计的等待时间
	 * @return Connection 可用连接或null
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
	 * 关闭所有连接,撤销驱动程序的注册
	 */
	public synchronized void release() {
		// 等待直到最后一个客户程序调用
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

				log("撤销JDBC驱动程序 " + driver.getClass().getName() + "的注册");
			} catch (SQLException e) {
				log(e, "无法撤销下列JDBC驱动程序的注册: " + driver.getClass().getName());
			}
		}
	}

	/**
	 * 根据指定属性创建连接池实例.此处可创建多个连接池
	 * 
	 * @param props
	 *            连接池属性
	 */
	private void createPools(Properties props) {
		Enumeration propNames = props.propertyNames();
		while (propNames.hasMoreElements()) {
			String name = (String) propNames.nextElement();
			if (name.endsWith(".url")) {
				String poolName = name.substring(0, name.lastIndexOf("."));
				String url = props.getProperty(poolName + ".url");
				if (url == null) {
					log("没有为连接池" + poolName + "指定URL");
					continue;
				}
				String user = props.getProperty(poolName + ".user");
				String password = props.getProperty(poolName + ".password");
				String maxconn = props.getProperty(poolName + ".maxconn", "0");
				int max;
				try {
					max = Integer.valueOf(maxconn).intValue();
				} catch (NumberFormatException e) {
					log("错误的最大连接数限制: " + maxconn + " .连接池: " + poolName);
					max = 0;
				}
				DBConnectionPool pool = new DBConnectionPool(poolName, url, user, password, max);
				pools.put(poolName, pool);
				log("成功创建连接池" + poolName);
			}
		}
	}

	/**
	 * 读取属性完成初始化
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
				System.err.println("不能读取属性文件. " + "请确保db.properties在CLASSPATH指定的路径中");
				return;
			}
//			String logFile = dbProps.getProperty("logfile", "DBConnectionManager.log");
//			try {
//
//				log = new PrintWriter(new FileWriter(logFile, true), true);
//			} catch (IOException e) {
//				System.err.println("无法打开日志文件: " + logFile);
//				log = new PrintWriter(System.err);
//			}
			loadDrivers(dbProps);
			createPools(dbProps);
		} catch (Exception e) {
		}
	}

	/**
	 * 171 * 装载和注册所有JDBC驱动程序 172 * 173 * @param props 属性 174
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
				log("成功注册JDBC驱动程序" + driverClassName);
			} catch (Exception e) {
				log("无法注册JDBC驱动程序: " + driverClassName + ", 错误: " + e);
			}
		}
	}

	/**
	 * 将文本信息写入日志文件
	 */
	private void log(String msg) {
		Log4jUtil.getLogger().debug(msg);
	}

	/**
	 * 将文本信息与异常写入日志文件
	 */
	private void log(Throwable e, String msg) {
		Log4jUtil.getLogger().debug(msg);
		Log4jUtil.getLogger().debug(MyUtil.Execption2String(e));
	}

}
