package com.cn.hsg.pojo;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;
import org.nutz.ioc.loader.annotation.IocBean;


/***
 * 
 * @View 视图概要：查”操作将针对 View
 *  声明一个实体的时候，有些时候你需要将它存在某一个Table中，但是你却希望从一个View中获取。
 *  这么做通常是希望View可以帮你做一些数据的统计方面的工作。
 *  Nutz.Dao 支持你为一个实体 声明一个 @Table 的同时也声明一个 @View，对于一个对象， Nutz.Dao的“增,删,改”操作将针对Table，而“查”操作将针对 View。
 *  实际上，如果你没有声明 @View， 那么你声明的@Table 将作为@View 的默认设置。
 * @author pengyucheng
 *
 */
@IocBean(singleton=false)
@Table("btbooks")
@View("v_btbooks")
public class btBooks {

	@Id
	@Column("btId")
	private int btId;
	
	@Column("btname")
	@Readonly
	@ColDefine(type=ColType.TEXT)
	private String btname;
	
	@Column("bturl")
	@Readonly
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
