package main;

import com.sun.xml.internal.ws.developer.Serialization;

import java.io.Serializable;

/**
 * @author Tian Qi
 * @version 1.0
 **/
public class Entity implements Serializable {

    public Entity() {
        System.out.println("Constructor called：构造器被调用");
    }

    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
