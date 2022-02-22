package com.kim.obj;

import com.kim.user.obj.Age;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AgeTest {

    @Test
    @DisplayName("정상적인 범위의 나이 입력")
    public void test1() {
        Age age1 = new Age(0);
        assertThat(new Age(0)).isEqualTo(age1);
    }

    @Test
    @DisplayName("비정상적인 범위의 나이 입력")
    public void test2() {
        assertThatThrownBy(() -> new Age(-30)).hasMessageContaining("나이의 올바른 입력 범위를 벗어납니다");
        assertThatThrownBy(() -> new Age(201)).hasMessageContaining("나이의 올바른 입력 범위를 벗어납니다");
    }

}
