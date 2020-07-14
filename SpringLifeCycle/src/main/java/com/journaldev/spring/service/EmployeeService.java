package com.journaldev.spring.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.journaldev.spring.bean.Employee;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class EmployeeService implements BeanPostProcessor, Ordered {

	@Override
	public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {

		if(o instanceof Employee){
			((Employee)o).setName("New name");
		}

		return o;
	}

	@Override
	public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
		if(o instanceof Employee){
			((Employee)o).setName("New name 2");
		}

		return o;
	}

	@Override
	public int getOrder() {
		return Integer.MAX_VALUE;
	}
}
