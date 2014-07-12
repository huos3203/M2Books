package com.cn.hsg.action;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import com.cn.hsg.pojo.Pet;

public class HelloPet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ioc ioc = new NutIoc(new JsonLoader("nutz/demo/ioc/pet/pets.js"));
		Pet pet = ioc.get(Pet.class, "xiaobai");
		System.out.printf("%s - [%s]\n", pet.getName(), pet.getBirthday().getTimeZone().getID());
		
		Pet xh = ioc.get(null, "xiaohei"); 
		System.out.printf("%s's friend is %s\n", xh.getName(),xh.getFriend().getName());
	}

}
