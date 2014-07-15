package com.cn.hsg.dao;


import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.cn.hsg.pojo.btBooks;
import com.forfuture.autoconfig.dto.newspaperdecr;



@IocBean
public class btBooksDao {

	@Inject
	private Dao dao;
	
	//重生时，添加关系表id:LINKID , Content:通知内容
	public boolean insertT(btBooks btBooks) {
		
//		log.info("新建通知消息："+notif.getLinkID());
//		log.info("dao:"+dao);
		boolean suc = false;
		dao.create(btBooks.class, false);
		Object tt = dao.insert(btBooks);
		if (tt!=null) {
			suc = true;
		}
		return suc;
	}
}
