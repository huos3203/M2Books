package com.cn.hsg.action;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.cn.hsg.pojo.Pet;

public class HelloPet {

	private static Log logger = Logs.getLog(HelloPet.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ioc ioc = new NutIoc(new JsonLoader("ioc/pets.js"));
		Pet pet = ioc.get(Pet.class, "xiaobai");
		System.out.printf("%s ss- [%s]\n", pet.getName(), pet.getBirthday().getTimeZone().getID());
		logger.infof("%s - [%s]\n", pet.getName(), pet.getBirthday().getTimeZone().getID());
		Pet xh = ioc.get(null, "xiaohei"); 
		System.out.printf("%s'dds friend is %s\n", xh.getName(),xh.getFriend().getName());
		logger.infof("%s's friend is %s\n", xh.getName(),xh.getFriend().getName());
	}

}
