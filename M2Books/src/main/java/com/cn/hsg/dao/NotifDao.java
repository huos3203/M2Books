package com.cn.hsg.dao;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.cn.hsg.pojo.Notification;



@IocBean
public class NotifDao {

	private static final Log log = Logs.get();
	@Inject
	private Dao dao;
	
	//重生时，添加关系表id:LINKID , Content:通知内容
	public boolean insertT(Notification notif) {
		log.info("新建通知消息："+notif.getLinkID());
		boolean suc = false;
		Object tt = dao.insert(notif);
		if (tt!=null) {
			suc = true;
		}
		return suc;
	}
}
