package ru.otus.spring.dao.role;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.model.Role;
import ru.otus.spring.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName roleName);
}
