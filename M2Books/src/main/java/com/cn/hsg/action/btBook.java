package com.cn.hsg.action;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;





import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.SetupBy;

import com.cn.hsg.dao.NotifDao;
import com.cn.hsg.pojo.Notification;
import com.cn.hsg.tools.Tools;
import com.cn.hsg.util.WendalSetup;


@IocBean
//@SetupBy(value = WendalSetup.class)
public class btBook {

	
	@Inject("refer:notifDao")
	private static NotifDao notifDao;
	@Inject("refer:notification")
	private static Notification notif;
	

	private static Ioc ioc;
	private static final Log log = Logs.get();
	private static btBook btBoo;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		/**
		 * 配置文件初始化相关实例
		 * args = new String[]{"ioc/btBook.js","ioc/dao.js"};
		 * JsonLoader js = new JsonLoader(args);
    	 * Ioc ioc = new NutIoc(js);
    	 * ioc.get(btBook.class,"btBook");
		 */
		
		/**
		 * Ioc - 混合加载器
		 * 
		 */
//		Ioc ioc;
		try {
			ioc = new NutIoc(new ComboIocLoader("*org.nutz.ioc.loader.json.JsonLoader",
					"ioc/dao.js",
//					"ioc/btBook.js",
					"*org.nutz.ioc.loader.annotation.AnnotationIocLoader", 
					"com.cn.hsg.action",
					"com.cn.hsg.dao",
					"com.cn.hsg.pojo"
					));
			
			ioc.get(btBook.class);
			//使用混合加载器ComboIocLoader,以下对象无法@Inject方法注入该类中，故有以下代替：
			notifDao = ioc.get(NotifDao.class);
			notif = ioc.get(Notification.class);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("notifDao:"+notifDao+"notif:"+notif);
		
//		Tools tools = new Tools();
		File f=new File("~/图书/");
        f.mkdirs();
		FileWriter fw=new FileWriter("~/图书/搜狐书本地址11.txt",false);
		
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
