package com.cn.hsg.util;

import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.nutz.resource.Scans;




public class WendalSetup implements Setup {

	private static final Log log = Logs.get();
	@Override
	public void init(NutConfig config) {
		// TODO Auto-generated method stub
		log.info("dao IOC config:"+config.getIoc());
		Dao dao = config.getIoc().get(Dao.class);
		
		for (Class<?>kClass : Scans.me().scanPackage("com.cn.hsg")) {
			if (kClass.getAnnotation(Table.class)!=null) {
				dao.create(kClass, false);
			}
		}
		
//		if (dao.count(UserToken.class)==0) {
//			UserToken user = new UserToken();
//			user.setId(1);
//			user.setLogname("admin");
//			user.setToken("123456");
//			dao.insert(user);
//		}
	}

	@Override
	public void destroy(NutConfig config) {
		// TODO Auto-generated method stub

	}

}
