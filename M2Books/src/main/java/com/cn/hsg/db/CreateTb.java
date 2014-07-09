package com.cn.hsg.db;

import java.io.IOException;




import com.cn.hsg.pojo.BooksZT;

import net.rile.sql.DataBaseDao;

//import 

public class CreateTb
{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		DataBaseDao dd=new DataBaseDao();
		
//		dd.create(Books.class, true);
		dd.create(BooksZT.class,true);
		java.util.List<BooksZT> booklist= dd.query(BooksZT.class, null, null);
		int t=0;	
//		while (t<=booklist.size()) {
//			System.out.println(booklist.get(3).getState());
//			t++;
//			}
		System.out.println(t);
		System.out.println("数据库表生成完成！！");
	}
}
