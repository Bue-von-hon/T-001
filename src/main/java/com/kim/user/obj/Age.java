package com.kim.user.obj;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Age {
    private final int age;

    public Age(int age) {
        if (age<0||age>200) {
            throw new RuntimeException("나이의 올바른 입력 범위를 벗어납니다");
        }
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

}
