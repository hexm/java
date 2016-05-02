package com.icbc.hexm.music.kugou;

public class KugouMusic {

	private String filename;
	private String extname;
	private String m4afilesize;
	private String k320hash;
	private String mvhash;
	private String privilege;
	private String filesize;
	private String bitrate;
	private String ownercount;
	private String topic;
	private String k320filesize;
	private String isnew;
	private String duration;
	private String singername;
	private String k320privilege;
	private String hash;
	private String srctype;
	private String sqfilesize;
	private String sqprivilege;
	private String sqhash;
	private String feetype;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getExtname() {
		return extname;
	}

	public void setExtname(String extname) {
		this.extname = extname;
	}

	public String getM4afilesize() {
		return m4afilesize;
	}

	public void setM4afilesize(String m4afilesize) {
		this.m4afilesize = m4afilesize;
	}

	public String getK320hash() {
		return k320hash;
	}

	public void setK320hash(String k320hash) {
		this.k320hash = k320hash;
	}

	public String getMvhash() {
		return mvhash;
	}

	public void setMvhash(String mvhash) {
		this.mvhash = mvhash;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getBitrate() {
		return bitrate;
	}

	public void setBitrate(String bitrate) {
		this.bitrate = bitrate;
	}

	public String getOwnercount() {
		return ownercount;
	}

	public void setOwnercount(String ownercount) {
		this.ownercount = ownercount;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getK320filesize() {
		return k320filesize;
	}

	public void setK320filesize(String k320filesize) {
		this.k320filesize = k320filesize;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KugouMusic [filename=");
		builder.append(filename);
		builder.append(", extname=");
		builder.append(extname);
		builder.append(", m4afilesize=");
		builder.append(m4afilesize);
		builder.append(", k320hash=");
		builder.append(k320hash);
		builder.append(", mvhash=");
		builder.append(mvhash);
		builder.append(", privilege=");
		builder.append(privilege);
		builder.append(", filesize=");
		builder.append(filesize);
		builder.append(", bitrate=");
		builder.append(bitrate);
		builder.append(", ownercount=");
		builder.append(ownercount);
		builder.append(", topic=");
		builder.append(topic);
		builder.append(", k320filesize=");
		builder.append(k320filesize);
		builder.append(", isnew=");
		builder.append(isnew);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", singername=");
		builder.append(singername);
		builder.append(", k320privilege=");
		builder.append(k320privilege);
		builder.append(", hash=");
		builder.append(hash);
		builder.append(", srctype=");
		builder.append(srctype);
		builder.append(", sqfilesize=");
		builder.append(sqfilesize);
		builder.append(", sqprivilege=");
		builder.append(sqprivilege);
		builder.append(", sqhash=");
		builder.append(sqhash);
		builder.append(", feetype=");
		builder.append(feetype);
		builder.append("]");
		return builder.toString();
	}

	public String getIsnew() {
		return isnew;
	}

	public void setIsnew(String isnew) {
		this.isnew = isnew;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getSingername() {
		return singername;
	}

	public void setSingername(String singername) {
		this.singername = singername;
	}

	public String getK320privilege() {
		return k320privilege;
	}

	public void setK320privilege(String k320privilege) {
		this.k320privilege = k320privilege;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getSrctype() {
		return srctype;
	}

	public void setSrctype(String srctype) {
		this.srctype = srctype;
	}

	public String getSqfilesize() {
		return sqfilesize;
	}

	public void setSqfilesize(String sqfilesize) {
		this.sqfilesize = sqfilesize;
	}

	public String getSqprivilege() {
		return sqprivilege;
	}

	public void setSqprivilege(String sqprivilege) {
		this.sqprivilege = sqprivilege;
	}

	public String getSqhash() {
		return sqhash;
	}

	public void setSqhash(String sqhash) {
		this.sqhash = sqhash;
	}

	public String getFeetype() {
		return feetype;
	}

	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}

}
