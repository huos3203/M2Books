package com.cn.hsg.action;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.nutz.el.opt.custom.Trim;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.cn.hsg.dao.NotifDao;
import com.cn.hsg.pojo.Notification;
import com.forfuture.tools.Tools;

@IocBean
public class CopyOfTxt {

	
	@Inject("refer:notifDao")
	private static NotifDao notifDao;
	@Inject("refer:notification")
	private static Notification notif;
	
	private static final Log log = Logs.get();
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Tools tools = new Tools();
		File f=new File("~/图书/");
        f.mkdirs();
		FileWriter fw=new FileWriter("~/图书/搜狐书本地址11.txt",false);
		for (int i = 0; i <= 100; i++) {
			String url1="http://btdigg.org/search?q="+"少女"+"&p="+(i+1); //搜狐图书首地址
			System.out.println("获取搜狐读书书籍路径："+url1);
			String htmls = "";
//			try {
//				htmls = tools.getContent(url1);
//			} catch (IOException e) {
//				System.out.println("获取搜狐读书书籍路径失败："+url1);
//				e.printStackTrace();
//			}
//			System.out.println("获取搜狐读书书籍路径："+htmls);
//			ArrayList<String> als = tools.getByREX(htmls, "<a onclick[^>]+>.+?</a>");
//			for (int a = 0; a < als.size(); a++) {
//				System.out.println(als.get(a));
//				String BookUrl = tools.getStrByREX(als.get(a), "<a href[^>]+>.+?</a>");//图书的url
////				BookUrl = tools.getLinkTagFromLinkStr(BookUrl,url1).getLink();
//				BookUrl = tools.Html2Text(BookUrl);
//				String ImgUrl = tools.getStrByREX(als.get(a), "<a onclick[^>]+>.+?</a>"); //封面的url
//				ImgUrl = tools.getLinkTagFromLinkStr(ImgUrl,url1).getLink();
////				String bookName = tools.getStrByREX(als.get(a), "<h3>.+?</h3>"); //图书名
////				bookName = tools.Html2Text(bookName).replace("&#8226;", "·");
////				String author = tools.getStrByREX(als.get(a), "作\\s*者.+?</span>");   		//作者
////				System.out.println(author);
////				author = tools.Html2Text(author).replace("作者", "").replace(":", "").replace("：", "").replace(" ", "").replace("&#8226;", "·").trim();
////				String press = tools.getStrByREX(als.get(a), "出\\s*版\\s*：.+?</span>").replaceAll("\\s+", "");
////				press = tools.Html2Text(press).replace("出版", "").replace(":", "").replace("：", "").replace(" ", "").replace("&#8226;", "·").trim();
////					booksZT.setBookName(bookName);
////					booksZT.setBookURL(BookUrl);
////					booksZT.setAuthor(author);
////					booksZT.setImgURL(ImgUrl);
////					booksZT.setPress(press);
////					booksZT.setState("No!!!");
////			        dbd.insert(booksZT);
//				//添加通知信息表，保存通知信息
//        		log.info("添加通知信息表，保存通知信息,关系表id:");
//        		notif.setLinkID(1);
//        		notif.setContent("");
//        		notif.setNoticeType(1);
//        		notifDao.insertT(notif);
//				fw.write("图书Url:"+BookUrl+"\r\n图书版图："+ImgUrl+"\r\n图书名："+BookUrl+"\r\n\r\n");
//			}
//			System.out.println("图书个数："+als.size()*(i+1));
			
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
