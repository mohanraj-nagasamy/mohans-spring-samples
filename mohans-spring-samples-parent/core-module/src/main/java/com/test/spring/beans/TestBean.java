package com.test.spring.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.spring.beans.anno.TestAnno;

public class TestBean {

	private Integer id;
	private String name;

	@Autowired()
	private TestAnno testAnno;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TestAnno getTestAnno() {
		return testAnno;
	}

	@Autowired
	public void setTestAnno(TestAnno testAnno) {
		this.testAnno = testAnno;
	}

	@PostConstruct
	public void populateMovieCache() {
		System.out.println("----------------------------");
		System.out.println("TestBean.populateMovieCache()");
	}

	@PreDestroy
	public void clearMovieCache() {
		System.out.println("----------------------------");
		System.out.println("TestBean.clearMovieCache()");
	}

}
