package com.googlecode.sandcode.helloworld;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author dmakariev
 */
@ManagedBean(name = "hi")
@javax.faces.bean.SessionScoped
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
