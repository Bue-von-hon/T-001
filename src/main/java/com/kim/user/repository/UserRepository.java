package com.kim.user.repository;

import com.kim.user.entity.User;
import com.kim.user.obj.Age;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAge(Age age);
}
