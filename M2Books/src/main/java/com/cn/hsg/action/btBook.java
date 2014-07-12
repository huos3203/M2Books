package com.cn.hsg.action;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.StringContent;

import org.nutz.dao.Dao;
import org.nutz.el.opt.custom.Trim;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.cn.hsg.dao.NotifDao;
import com.cn.hsg.pojo.Notification;
import com.cn.hsg.tools.Tools;

@IocBean
public class btBook {

	
//	@Inject("refer:notifDao")
	private static NotifDao notifDao;
//	@Inject("refer:notification")
	private static Notification notif;
	

	private static final Log log = Logs.get();
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		args = new String[]{"ioc/btBook.js","ioc/dao.js"};
		JsonLoader js = new JsonLoader(args);
    	//将配置信息保存到dao.xml,并存放于src文件夹下
    	Ioc ioc = new NutIoc(js);
//    	DataSource ds = ioc.get(DataSource.class);
//    	Dao dao = new NutDao(ds); //如果已经定义了dao,那么改成dao =

    	ioc.get(btBook.class,"btBook");
		
		log.info("btBook:"+"notifDao:"+notifDao+"notif"+notif+"dao:"+notifDao.dao);
		
//		Tools tools = new Tools();
		File f=new File("~/图书/");
        f.mkdirs();
		FileWriter fw=new FileWriter("~/图书/搜狐书本地址11.txt",false);
		
		notif = new Notification();
		notifDao = new NotifDao();
		for (int i = 0; i <= 100; i++) {
			String url1="http://btdigg.org/search?q="+"少女"+"&p="+(i+1); //搜狐图书首地址
			System.out.println("获取搜狐读书书籍路径："+url1);
			
			log.info("添加通知信息表，保存通知信息,关系表id:");
			notif.setLinkID(1);
			notif.setContent("");
			notif.setNoticeType(1);
			notifDao.insertT(notif);
		}
		
		fw.close();
		System.out.println("操作成功！！！");
	}

	
}
