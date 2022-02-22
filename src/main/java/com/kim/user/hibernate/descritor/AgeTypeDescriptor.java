package com.kim.user.hibernate.descritor;

import com.kim.user.obj.Age;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;


public class AgeTypeDescriptor extends AbstractTypeDescriptor<Age> {
    public static final AgeTypeDescriptor INSTANCE = new AgeTypeDescriptor();

    protected AgeTypeDescriptor() {
        super(Age.class);
    }

    @Override
    public Age fromString(String s) {
        if (s.isEmpty()) return null;
        int val =  Integer.parseInt(s);
        return new Age(val);
    }

    @Override
    public <X> X unwrap(Age age, Class<X> type, WrapperOptions wrapperOptions) {
        if (age == null) return null;
        if (Age.class.isAssignableFrom(type)) {
            return (X) age;
        }
        if (String.class.isAssignableFrom(type)) {
            return (X) toString(age);
        }
        if (Integer.class.isAssignableFrom(type)) {
            return (X) Integer.valueOf(age.getAge());
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> Age wrap(X value, WrapperOptions wrapperOptions) {
        if (value == null) return null;
        if (String.class.isInstance(value)) {
            fromString((String) value);
        }
        if (Age.class.isInstance(value)) {
            return (Age) value;
        }
        if (Integer.class.isInstance(value)) {
            return new Age((Integer) value);
        }
        throw  unknownWrap(value.getClass());
    }
}