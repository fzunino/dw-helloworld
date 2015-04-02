/*
 * Copyright (c) 2015 Zauber S.A.  -- All rights reserved
 */
package com.flowics.dropwizard.helloworld.core;

/**
 * TODO: Description of the class, Comments in english by default  
 * 
 * 
 * @author Fernando Zunino
 * @since Apr 2, 2015
 */
public class SamplePojo {

    private final String name;
    private final int value;

    public SamplePojo(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
