package ru.otus.spring.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
