package com.cn.hsg.action;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;




import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.SetupBy;

import com.cn.hsg.dao.btBooksDao;
import com.cn.hsg.pojo.btBooks;
import com.cn.hsg.tools.Tools;
import com.cn.hsg.util.WendalSetup;


@IocBean
//@SetupBy(value = WendalSetup.class)
public class btBook {

	
	@Inject("refer:btBooksDao")
	private static btBooksDao btBooksDao;
	@Inject("refer:btBooks")
	private static btBooks btBooks;
	

	private static Ioc ioc;
	private static Log log = Logs.getLog(btBook.class);
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
			btBooksDao = ioc.get(btBooksDao.class);
			btBooks = ioc.get(btBooks.class);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("notifDao:"+btBooksDao+"notif:"+btBooks);
		
		Tools tools = new Tools();
		File f=new File("~/图书/");
        f.mkdirs();
		FileWriter fw=new FileWriter("~/图书/搜狐书本地址11.txt",false);
		
		String keyword = "English";
		int z = 0;
		for (int i = 38; i <= 100; i++) {
			z = i + 1;
			String url1="http://btdigg.org/search?q="+"少女"+"&p="+ z; //搜狐图书首地址
			log.info("获取搜狐读书书籍路径："+url1);
			String htmlString = "";
//			Document doc = null;
			try {
				htmlString = tools.getContent(url1); 
//				doc = Jsoup.connect(url1).get();
//				String title = doc.title();
			} catch (IOException e) {
				log.info("获取搜狐读书书籍路径失败："+url1);
				e.printStackTrace();
			}
			ArrayList<String> arlist = new ArrayList<String>();
			arlist = tools.getByREX(htmlString, "<td class=\"torrent_name\">.+?<pre");
//			Elements els = doc.getElementsByTag("a");
			for (int j = 0; j < arlist.size(); j++) {
				log.info("页码："+i+"中有"+arlist.size()+"个");
				String btname = tools.getStrByREX(arlist.get(j), "<a href=\"/search[^>]+>.+?</a>");
				btname = tools.Html2Text(btname);
				
				String btUrl = tools.getStrByREX(arlist.get(j), "<a onclick[^>]+>.+?</a>");				
				btUrl = getA_href(btUrl);

				String btsize = tools.getStrByREX(htmlString, "(大小|Size):</span>.?<span class=\"attr_val\">.+?</span>");
				if (btsize!=null) {
					btsize = tools.Html2Text(btsize).replaceAll("(大小|Size):", "");
				}
				String filecount = tools.getStrByREX(htmlString, "(文件数|Files):</span>.?<span class=\"attr_val\">\\d{0,9}</span>");
				if(filecount!=null)
					filecount = tools.Html2Text(filecount).replaceAll("(文件数|Files):", "");
				
				String downnum = tools.getStrByREX(htmlString, "(总下载数|Downloads):</span>.?<span class=\"attr_val\">\\d{0,9}</span>");
				if(downnum!=null)
					downnum = tools.Html2Text(downnum).replaceAll("(总下载数|Downloads):", "");
				
				String createtime = tools.getStrByREX(htmlString, "(添加时间|AddTime):.?<span class=\"attr_val\">.+?</span>");
				if(createtime!=null)
					createtime = tools.Html2Text(createtime).replace("(添加时间|AddTime):", "");
			
				btBooks.setBtname(btname);
				btBooks.setBturl(btUrl);
				btBooks.setBtsize(btsize);
				btBooks.setFilecount(filecount);
				btBooks.setDownnum(downnum);
				btBooks.setPage(z+"");
				btBooks.setKeyword(keyword);
				btBooksDao.insertT(btBooks);
			}
			
		}		
		fw.close();
		log.info("操作成功！！！");
	}

	public static String getA_href(String linkstr) {
		
		String linkHref="";
		Document doc = Jsoup.parse(linkstr);
		Elements links = doc.getElementsByTag("a");
		
		for (Element link : links) {
			  linkHref = link.attr("href");
		}
		return linkHref;
	}
}
