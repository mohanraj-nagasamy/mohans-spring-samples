package com.test.core.package1.anno;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.test.core.package1.TestBean;

@Component(value = "mohanAnno")
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class TestAnno {
	@Resource
	private ApplicationContext context;

	public ApplicationContext getContext() {
		System.out.println("TestAnno.getContext() - by using @Resource");

		Object str = context.getBean("test");
		TestBean testBean = (TestBean) context.getBean("testBean");
		System.out.println("str --> " + str);
		System.out.println("testBean --> " + testBean);
		System.out.println("testBean ID --> " + testBean.getId());
		System.out.println("testBean Name --> " + testBean.getName());

		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

}
