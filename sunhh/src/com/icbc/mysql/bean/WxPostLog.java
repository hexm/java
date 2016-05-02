package com.icbc.mysql.bean;

import java.sql.Timestamp;

public class WxPostLog {
	private String gzh_name;
	private String from_username;
	private String to_username;
	private String msg_id;
	private String msg_type;
	private String msg_content;
	private String create_time;
	private Timestamp update_date;

	public String getGzh_name() {
		return gzh_name;
	}

	public void setGzh_name(String gzhName) {
		gzh_name = gzhName;
	}

	public String getFrom_username() {
		return from_username;
	}

	public void setFrom_username(String fromUsername) {
		from_username = fromUsername;
	}

	public String getTo_username() {
		return to_username;
	}

	public void setTo_username(String toUsername) {
		to_username = toUsername;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msgId) {
		msg_id = msgId;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msgType) {
		msg_type = msgType;
	}

	public String getMsg_content() {
		return msg_content;
	}

	public void setMsg_content(String msgContent) {
		msg_content = msgContent;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String createTime) {
		create_time = createTime;
	}

	public Timestamp getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Timestamp updateDate) {
		update_date = updateDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxPostLog [create_time=");
		builder.append(create_time);
		builder.append(", from_username=");
		builder.append(from_username);
		builder.append(", gzh_name=");
		builder.append(gzh_name);
		builder.append(", msg_content=");
		builder.append(msg_content);
		builder.append(", msg_id=");
		builder.append(msg_id);
		builder.append(", msg_type=");
		builder.append(msg_type);
		builder.append(", to_username=");
		builder.append(to_username);
		builder.append(", update_date=");
		builder.append(update_date);
		builder.append("]");
		return builder.toString();
	}

}
