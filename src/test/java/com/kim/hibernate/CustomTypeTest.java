package com.kim.hibernate;

import com.kim.entity.User;
import com.kim.obj.Age;
import com.kim.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomTypeTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("커스텀한 타입이 동작하는지 확인")
    public void test1() {
        Age age = new Age(10);
        User user1 = User.builder()
                .id(1L)
                .age(age)
                .build();
        userRepository.save(user1);
        assertThat(userRepository.findById(1L).get()).isEqualTo(new User(1L, age));
    }

}
