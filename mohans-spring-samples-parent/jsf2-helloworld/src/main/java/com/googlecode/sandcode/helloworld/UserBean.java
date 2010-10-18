package com.googlecode.sandcode.helloworld;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserBean {
    private String name;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String send() {
	return "hello";
    }
}