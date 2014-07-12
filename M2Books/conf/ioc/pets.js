/*
* 开始写上 var ioc = { , 是为了利用 eclipse 的 javascript 编辑器的自动
格式化功能 
*/
var ioc = { 
		/*
		 * 默认的,你仅仅需要直接声明每个字段的值即可,Nutz.Ioc 会为你转 型
		 */ 
		xiaobai : {
					name : 'XiaoBai',
					birthday : '2009-10-25 15:23:40' 
				  },

		/*
		* 你当然也可以做更细致的设置 
		* */
		xiaohei : {
					type : 'nutz.demo.ioc.book.Pet', // 类型
					singleton : false, 			     // 是否为单件,为false时,那么它每次都会生成一个新的实例
					args : [ 'XiaoHei' ], 			 // 构造函数参数 
					
					events : {						 // 通过实现一个触发器,IocEventTrigger方法监听对象事件
						fetch : 'nutz.demo.ioc.book.OnFetchPet'
						fetch : 'onFetch'
						},
				  
					fields : {
						birthday : '2009-11-3 08:02:14',
						friend : {
									refer : 'xiaobai'
								 }						// 指向容器里另外一个对象 
						}
				   }			
}