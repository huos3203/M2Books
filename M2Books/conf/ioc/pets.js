var ioc = { 
		/*
		 * 默认的,你仅仅需要直接声明每个字段的值即可,Nutz.Ioc 会为你转型
		 */ 
		btBook : {
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
//						fetch : 'onFetch'            //监听对象的第二种方法:通过对象自身的一个函数 ,这个函数必须为 public,并且不能有参数
//							* create: 对象创建时触发
//							* fetch: 对象获取时触发
//							* depose: 对象销毁时触发
						},
				  
					fields : {
						birthday : '2009-11-3 08:02:14',
						friend : {
									refer : 'xiaobai'
								 }						// 指向容器里另外一个对象 
						}
				   }			
}