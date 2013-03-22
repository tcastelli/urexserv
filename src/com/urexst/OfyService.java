package com.urexst;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.urexst.model.News;
import com.urexst.model.Student;


public class OfyService {
    static {
        
    	factory().register(News.class);
    	factory().register(Student.class);
        
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}