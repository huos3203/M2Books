//var ioc = {
//	dataSource:{
//		type:"com.alibaba.druid.pool.DruidDataSource",
//		fields:{
//			driverClassName:"org.h2.Driver",
//			url:"jdbc:h2:nutz"
//		},
//		events : {
//			depose : "close"
//		}
//	},
//	
//	dao : {
//		type : "org.nutz.dao.impl.NutDao",
//		args : [{refer:"dataSource"}]
//	}	
//};

/**
 * 	远程服务器操作SqlServer数据库
 **/
//var ioc = {
//		dataSource : {
//			type:"org.apache.commons.dbcp.BasicDataSource",
//			
//			fields:{
//				driverClassName:"com.microsoft.sqlserver.jdbc.SQLServerDriver",
//				url:"jdbc:sqlserver://192.168.86.31:1433;DatabaseName = fileout",  //发布:db.pyc.com.cn   //测试：192.168.86.31
//				username : 'protect',
//				password : 'admin@pyc.bj'
//			},
//			events : {
//					depose : "close"
//			}
//		},
//		dao : {
//			type : "org.nutz.dao.impl.NutDao",
//			args : [{refer:"dataSource"}]
//		},
//		
//		userTokenDao : {
//			type : "com.test.apple.dao.UserTokenDao",
////			args : [{refer:"dao"}],
//			fields : {
//             dao : {refer : 'dao'}    // 指向容器里另外一个对象
//			}
//		},
//		
//		notifRelaDao : {
//			type : "com.test.apple.dao.NotifRelaDao",
//			fields : {
//				dao : {refer : 'dao'}
//			}
//			
//		}
//		
//};

var ioc = {
		dataSource : {
			type:"org.apache.commons.dbcp.BasicDataSource",
			
			fields:{
				driverClassName:"com.mysql.jdbc.Driver",
				url:"jdbc:mysql://192.168.81.169:3306/ebooks?useUnicode=true&characterEncoding=utf-8",
				username : 'root',
				password : ''
			},
			events : {
					depose : "close"
			}
		},
		dao : {
			type : "org.nutz.dao.impl.NutDao",
			args : [{refer:"dataSource"}]
		}//,
//	notif : {
//			type:"com.cn.hsg.pojo.Notification"
//	},
//   notifDao : {
//	   type : "com.cn.hsg.dao.NotifDao",
//	   fields : {
//		   dao : { refer : 'dao'}
//	   }
//   },
//   btBook : {
//	   type : "com.cn.hsg.action.btBook",
//	   fields : {
//		   notifDao : {refer:'notifDao'},
//		   notif : {refer : 'notif'}
//	   }
//   }
		
};