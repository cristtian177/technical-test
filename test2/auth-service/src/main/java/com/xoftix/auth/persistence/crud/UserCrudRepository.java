package com.xoftix.auth.persistence.crud;

import com.xoftix.auth.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<User, String> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

}