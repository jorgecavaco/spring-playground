package com.journaldev.spring.main;

import com.journaldev.spring.bean.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.journaldev.spring.service.EmployeeService;
import com.journaldev.spring.service.MyEmployeeService;

public class SpringMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

		System.out.println("Spring Context initialized");
		
		Employee employee = ctx.getBean("employee", Employee.class);

		System.out.println("Employee Name="+ employee.getName());
		
		ctx.close();
		System.out.println("Spring Context Closed");
	}

}
