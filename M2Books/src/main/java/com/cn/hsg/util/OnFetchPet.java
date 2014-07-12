package com.cn.hsg.util;

import org.nutz.ioc.IocEventTrigger;

import com.cn.hsg.pojo.Pet;

public class OnFetchPet implements IocEventTrigger<Pet> {

	@Override
	public void trigger(Pet pet) {
		// TODO Auto-generated method stub
		pet.setFetchCount(pet.getFetchCount() + 1);
	}

}
