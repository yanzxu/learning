package com.spring;

import com.spring.ioc.MyBeanFactoryImpl;
import com.spring.ioc.Student;
import com.spring.ioc.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLearningApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringLearningApplication.class, args);

		MyBeanFactoryImpl beanFactory = new MyBeanFactoryImpl();
		User user1 = (User)beanFactory.getBeanByName("com.spring.ioc.User");
		User user2 = (User)beanFactory.getBeanByName("com.spring.ioc.User");
		Student student1 = user1.getStudent();
		Student student2 = user1.getStudent();
		Student student3 = (Student)beanFactory.getBeanByName("com.spring.ioc.Student");
		System.out.println(user1);
		System.out.println(user2);
		System.out.println(student1);
		System.out.println(student2);
		System.out.println(student3);
	}

}
