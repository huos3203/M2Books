package com.cn.hsg.pojo;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(singleton=false)
@Table("btbooks")
public class btBooks {

	@Id
	@Column("btId")
	private int btId;
	
	@Column("btname")
	@ColDefine(type=ColType.TEXT)
	private String btname;
	
	@Column("bturl")
	@ColDefine(type=ColType.TEXT)
	private String bturl;

	@Column("btsize")
	@ColDefine(type=ColType.VARCHAR,width=200)
	private String btsize;
	
	@Column("filecount")
	@ColDefine(type=ColType.VARCHAR,width=200)
	private String filecount;
	
	@Column("downnum")
	@ColDefine(type=ColType.VARCHAR,width=200)
	private String downnum;
	
	@Column("page")
	@ColDefine(type=ColType.VARCHAR,width=200)
	private String page;
	
	@Column("keyword")
	@ColDefine(type=ColType.VARCHAR,width=100)
	private String keyword;

	public int getBtId() {
		return btId;
	}

	public void setBtId(int btId) {
		this.btId = btId;
	}

	public String getBtname() {
		return btname;
	}

	public void setBtname(String btname) {
		this.btname = btname;
	}

	public String getBturl() {
		return bturl;
	}

	public void setBturl(String bturl) {
		this.bturl = bturl;
	}

	public String getBtsize() {
		return btsize;
	}

	public void setBtsize(String btsize) {
		this.btsize = btsize;
	}

	public String getFilecount() {
		return filecount;
	}

	public void setFilecount(String filecount) {
		this.filecount = filecount;
	}

	public String getDownnum() {
		return downnum;
	}

	public void setDownnum(String downnum) {
		this.downnum = downnum;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
