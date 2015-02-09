package com.cn.hsg.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.ldap.Rdn;
import javax.security.auth.callback.Callback;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.cri.SqlExpression;
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
		
		/**
		 * 自定义 SQL
		 * 
		 * Sql 对象 -- org.nutz.dao.sql.Sql
		 * 
		 * 支持占位符的书写方式
		 * 变量占位符的定义：正则表达式[$][a-zA-Z0-9_-]
		 * 参数占位符的定义：正则表达式[@][a-zA-Z0-9_-]
		 * 
		 * 回调对象实现接口 org.nutz.dao.sql.SqlCallback
		 *
		 *  回调函数的返回值会存放在 Sql 对象中,调用 sql.getResult() 可以直接返回这个对象
			* sql.getList() 以及 sql.getObject() 方法会泛型安全的替你转型
			* 如果你的对象类型符合要求,则直接返回,否则会通过 Nutz.Castors 替你转换。
			* 对于 getList(),泛型参数用来描述集合内部元素的类型
			sql.getInt() 会安全的替你将结果转成 int,如果它可以被转成 int 的话,以下是我能想到的
			列表:
			* 字符串
			* 各种数字类型 * 字符
			* 布尔类型
		 *
		Sql sql = Sqls.create("select bturl,btname,btsize,downnum FROM $table where downnum > @downnum limit $topNum");
		sql.vars().set("table","t_abc");
		sql.vars().set("topNum",topNum);
		sql.params().set("downnum",5);
		
		sql.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				// TODO Auto-generated method stub
				List<btBooks> list = new LinkedList<btBooks>();
				while (rs.next()){
					btBooks btBooks = new btBooks();
					btBooks.setBturl(rs.getString("bturl"));
					btBooks.setBtsize(rs.getString("btsize"));
					btBooks.setDownnum(rs.getString("downnum"));
					list.add(btBooks);
				}
				return list;
			}
		}
		);
		dao.execute(sql);
		return sql.getList(btBooks.class);
		// Nutz内置了大量回调, 请查看Sqls.callback的属性
		*/
	}
}


/**
 * 动态实体
 * Map 只要多一个固定的名值对 ".table",那么nutz.dao就能知道要操作的数据库表名
 * 
 * Map<String,Object> map = new HashMap<String,Object>(); map.put(".table", "t_person");
	map.put("name", "abc");
	map.put("age", 18);
	dao.update(map);
*/