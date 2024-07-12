package com.Secuirty.secuirtyproject.Repository;
import com.Secuirty.secuirtyproject.Entities.Role;
import com.Secuirty.secuirtyproject.Entities.User;
import com.Secuirty.secuirtyproject.Services.Implementation.EncryptImpl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
    User findByRole(Role role);
    boolean existsByEmail(String email);

    default Optional<User> findByEncryptedEmail(String encryptedEmail) {
        return findByEmail(EncryptImpl.decrypt(encryptedEmail));
    }
}
