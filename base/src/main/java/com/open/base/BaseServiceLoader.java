package com.open.base;

import java.util.ServiceLoader;

public final class BaseServiceLoader {

    private BaseServiceLoader(){}

    public static<S> S load(Class<S> sClass){
        try{
            return ServiceLoader.load(sClass).iterator().next();
        }catch (Exception e){
            return null;
        }
    }
}
