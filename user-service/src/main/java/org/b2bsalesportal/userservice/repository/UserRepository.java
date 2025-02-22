package org.b2bsalesportal.userservice.repository;

import org.b2bsalesportal.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);

    User findByEmail(String username);
}
