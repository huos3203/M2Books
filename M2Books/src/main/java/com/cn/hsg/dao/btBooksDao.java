package com.cn.hsg.dao;


import java.util.ArrayList;
import java.util.List;

import javax.naming.ldap.Rdn;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.entity.Record;
import org.nutz.dao.util.cri.Static;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.trans.Atom;

import com.cn.hsg.pojo.btBooks;
import com.forfuture.autoconfig.dto.newspaperdecr;



@IocBean
public class btBooksDao {

	@Inject
	private Dao dao;
	
	private Log logger = Logs.getLog(btBooksDao.class);
	//重生时，添加关系表id:LINKID , Content:通知内容
	public boolean insertT(ArrayList<btBooks>  btBookslist) {
		
		logger.info("将插入数据的数据量："+btBookslist.size());
//		log.info("dao:"+dao);
		boolean suc = false;
		dao.create(btBooks.class, false);
		Object tt = dao.insert(btBookslist);
		if (tt!=null) {
			suc = true;
			logger.info("数据成功录入数据库中");
		}
		return suc;
	}
	
	/**
	 * 获取前 topNum 个数据
	 * @param topNum
	 * @return
	 */
	private List<btBooks> btlist = null;
	public List<btBooks> selectTop(final int topNum) {
		
		
	
		/**
		 * sql语句中TOP子语句的不同用法：
		 * 
		 * Sqlserver：
		 * SELECT TOP number|percent column_name(s) FROM table_name
		 * 
		 * MySql：
		 * SELECT column_name(s) FROM table_name LIMIT number
		 * 
		 * Cnd.wrap("原生sql语句")  limit 不能直接放在where关键字之后，需有其他条件才行，因为:limit asc ,desc,是同一类型关键字
		 * 
		 * btlist =dao.query(btBooks.class, Cnd.wrap("downnum > 5 limit "+topNum), null);
		 * 
		 * dao.createPager(1,10) ，用分页形式实现TOP功能
		 * 
		 *在没有构建pojo时，使用Record对象，雷同 rs
		 *List<Record> list = dao.query("btBooks", Cnd.where("name","LIKE","A%"), dao.createPager(1,topNum));
		 *
		 */

		/**
		 * 过滤字段
		 * 
		 * 某些时候,尤其是在更新对象的时候,用户希望忽略某些字段。通过注解 Nutz.Dao 实体注解 @Readonly 可以达到 这个目的。
		 * 但是更多的时候,对于 POJO 对象,只有在运行时,用户的 程序才能决定哪些字段更新,哪些不更新。@Readonly 注解可就达不到这个目的了。
		 * 
		 */
		
		FieldFilter filefilter = FieldFilter.create(btBooks.class, "^bturl|name$");
		filefilter.run(new Atom() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 查询所有以 A 开头的 Pet,返回前10个
				logger.info("查询数据库，通过正则：^url|name$ 过滤字段");
				btlist = (List<btBooks>) dao.query(btBooks.class, Cnd.where(null).asc("downnum").desc("btsize"), dao.createPager(0,topNum));
				
				logger.info("过滤后的数据数:"+btlist.size());
			}
			
		});
		
		
		return btlist;
		
	}
}
