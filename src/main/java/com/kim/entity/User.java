package com.kim.entity;

import com.kim.hibernate.type.AgeType;
import com.kim.obj.Age;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@TypeDef( name = "age", defaultForType = Age.class, typeClass = AgeType.class )
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "USERS")
@EqualsAndHashCode
public class User {

    @Id @GeneratedValue
    private Long id;

    private Age age;
}
