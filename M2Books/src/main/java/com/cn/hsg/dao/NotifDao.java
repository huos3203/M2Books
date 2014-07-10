package com.cn.hsg.dao;

import javax.sql.DataSource;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.cn.hsg.pojo.Notification;



@IocBean
public class NotifDao {

	private static final Log log = Logs.get();
	
	//重生时，添加关系表id:LINKID , Content:通知内容
	public boolean insertT(Notification notif) {
		JsonLoader js = new JsonLoader("ioc/dao.js");
    	//将配置信息保存到dao.xml,并存放于src文件夹下
    	Ioc ioc = new NutIoc(js);
    	DataSource ds = ioc.get(DataSource.class);
    	Dao dao = new NutDao(ds); //如果已经定义了dao,那么改成dao =

    	ioc.get(Dao.class);
		log.info("新建通知消息："+notif.getLinkID());
		boolean suc = false;
		Object tt = dao.insert(notif);
		if (tt!=null) {
			suc = true;
		}
		return suc;
	}
}
