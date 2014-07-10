package com.cn.hsg;

import javax.sql.DataSource;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.ioc.loader.xml.XmlIocLoader;

import com.cn.hsg.pojo.Books;
import com.cn.hsg.pojo.Notification;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	JsonLoader js = new JsonLoader("ioc/dao.js");
    	//将配置信息保存到dao.xml,并存放于src文件夹下
    	Ioc ioc = new NutIoc(js);
    	DataSource ds = ioc.get(DataSource.class);
    	Dao dao = new NutDao(ds); //如果已经定义了dao,那么改成dao =

    	ioc.get(Dao.class);
    	dao.create(Books.class, true);
    	dao.create(Notification.class, false);
//    	Books books = new Books();
//    	dao.insert(books);
    	ioc.depose(); //关闭Ioc容器
        System.out.println( "Hello World!" );
    }
}
