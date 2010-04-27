package com.jsf2.test.common.web;

/**
 *
 * @author Dimitar Makariev
 */
public interface ParameterResolver<T> {

    T resolve(String name);
}
