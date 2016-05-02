package com.icbc.mysql.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.icbc.hexm.util.Log4jUtil;
import com.icbc.mysql.bean.WxPostLog;
import com.icbc.mysql.util.DBUtils;

public class WxpostlogManager {

	public static int add(WxPostLog wxPostLog) {
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DBUtils.getConnection();
			if (!conn.isClosed()) {
				statement = conn.createStatement();
				String insertValues = "'" + wxPostLog.getGzh_name() + "','" + wxPostLog.getFrom_username() + "','" + wxPostLog.getTo_username()
						+ "','" + wxPostLog.getMsg_id() + "','" + wxPostLog.getMsg_type() + "','" + wxPostLog.getMsg_content() + "','"
						+ wxPostLog.getCreate_time() + "'";
				String sql = "insert into wx_post_log(gzh_name,from_username,to_username,msg_id,msg_type,msg_content,create_time) values ("
						+ insertValues + ")";
				Log4jUtil.getLogger().debug("WxpostlogManager.add sql:" + sql);
				int rs = statement.executeUpdate(sql);
				return rs;
			}
		} catch (SQLException e) {
			Log4jUtil.getLogger().debug("WxpostlogManager.add exception:", e);
		} finally {
			DBUtils.closeResources(conn, statement, null);
		}
		return 1;
	}

	public static int logWexinRequest(String gzhName, Map<String, String> requestMap) {

		WxPostLog wxPostLog = new WxPostLog();
		wxPostLog.setGzh_name(gzhName);
		wxPostLog.setFrom_username(requestMap.get("FromUserName"));
		wxPostLog.setMsg_content(requestMap.get("Content"));
		wxPostLog.setMsg_id(requestMap.get("MsgId"));
		wxPostLog.setMsg_type(requestMap.get("MsgType"));
		wxPostLog.setTo_username(requestMap.get("ToUserName"));
		wxPostLog.setCreate_time(requestMap.get("CreateTime"));
		add(wxPostLog);
		return 0;

	}

}
