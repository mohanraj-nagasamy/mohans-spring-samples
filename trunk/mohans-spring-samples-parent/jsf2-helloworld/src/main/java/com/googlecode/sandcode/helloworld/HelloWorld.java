package com.googlecode.sandcode.helloworld;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author dmakariev
 */
@ManagedBean(name = "hi")
@RequestScoped
public class HelloWorld {

    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public String greetFrom(String pageName) {
        return "Hello World ! ( " + pageName + " )";
    }
}
