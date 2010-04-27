package com.jsf2.test.app.web;

import javax.annotation.ManagedBean;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Dimitar Makariev
 */
@ManagedBean("hi")
@Scope("request")
public class HelloWorld {

    private String name;   

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
