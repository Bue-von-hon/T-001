package com.kim.user.hibernate.type;

import com.kim.user.hibernate.descritor.AgeTypeDescriptor;
import com.kim.user.obj.Age;
import org.hibernate.dialect.Dialect;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.DiscriminatorType;
import org.hibernate.type.descriptor.sql.IntegerTypeDescriptor;

public class AgeType extends AbstractSingleColumnStandardBasicType<Age> implements DiscriminatorType<Age> {
    public static final AgeType INSTANCE = new AgeType();
    public AgeType() {
        super(IntegerTypeDescriptor.INSTANCE, AgeTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "age";
    }

    @Override
    public Age stringToObject(String value) throws Exception {
        return fromString(value);
    }

    @Override
    public String objectToSQLString(Age age, Dialect dialect) throws Exception {
        return toString(age);
    }
}
