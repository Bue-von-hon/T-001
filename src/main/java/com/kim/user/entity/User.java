package com.kim.user.entity;

import com.kim.user.hibernate.type.AgeType;
import com.kim.user.obj.Age;
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
