package com.cn.hsg.pojo;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(singleton=false)
@Table("tb_notification")
public class Notification {

	@Id
	@Column("noticeID")
	private int noticeID;
	
	@Column("LINKID")
	@ColDefine(type=ColType.INT)
	private int linkID;
	
	@Column("Content")
	@ColDefine(type=ColType.VARCHAR,width = 256)
	private String content;

	@Column("noticeType")
	@ColDefine(type=ColType.INT)
	private int noticeType;
//	@One(target=NotificationRelation.class,field="LINKID") 
//	private NotificationRelation notificRelation;
	
	
	

	public int getNoticeID() {
		return noticeID;
	}

	public void setNoticeID(int noticeID) {
		this.noticeID = noticeID;
	}

	public int getLinkID() {
		return linkID;
	}

	public void setLinkID(int linkID) {
		this.linkID = linkID;
	}

	
	public int getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(int noticeType) {
		this.noticeType = noticeType;
	}

//	public NotificationRelation getNotificRelation() {
//		return notificRelation;
//	}
//
//	public void setNotificRelation(NotificationRelation notificRelation) {
//		this.notificRelation = notificRelation;
//	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
