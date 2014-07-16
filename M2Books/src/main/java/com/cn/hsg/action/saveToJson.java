package com.cn.hsg.action;

import java.util.List;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.cn.hsg.dao.btBooksDao;
import com.cn.hsg.pojo.btBooks;

public class saveToJson {

	private static Log logger = Logs.get(); 
	
	private static btBooksDao btBooksDao;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		logger.info("将数据库中的数据转存为JSON格式");
		String[] paths = new String[]{"ioc/dao.js","ioc/btBook.js"};
		Ioc ioc = new NutIoc(new JsonLoader(paths));
		ioc.get(saveToJson.class,"savetojson");
		//从数据库中读取源数据
		List<btBooks> btList = btBooksDao.selectTop(10);
		logger.info("对象转为json为:"+Json.toJson(btList));
	}

}
