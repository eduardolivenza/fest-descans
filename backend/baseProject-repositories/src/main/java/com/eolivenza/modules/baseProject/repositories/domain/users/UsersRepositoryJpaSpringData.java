package com.eolivenza.modules.baseProject.repositories.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepositoryJpaSpringData extends JpaRepository<UserJpa, String> {
}
