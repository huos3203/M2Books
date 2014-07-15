/*
* 开始写上 var ioc = { , 是为了利用 eclipse 的 javascript 编辑器的自动
格式化功能 
*/
var ioc = { 
		/*
		 * 默认的,你仅仅需要直接声明每个字段的值即可,Nutz.Ioc 会为你转 型
		 */ 
   btBooks : {
			type:"com.cn.hsg.pojo.btBooks"
	},
	btBooksDao : {
	   type : "com.cn.hsg.dao.btBooksDao",
	   fields : {
		   dao : { refer : 'dao'}
	   }
   },
   btBook : {
	   type : "com.cn.hsg.action.btBook",
	   fields : {
		   notifDao : {refer:'btBooksDao'},
		   notif : {refer : 'btBooks'}
	   }
   }
};