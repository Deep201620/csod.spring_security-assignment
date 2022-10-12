package demo.csod.securitydemo.csod.spring_security.utils;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;

public class DatabaseInterceptor extends EmptyInterceptor {

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        return super.onSave(entity, id, state, propertyNames, types);
    }
}
