package com.novikovkirill.test.accountingsystem.repositories;

import com.novikovkirill.test.accountingsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для обращений
 */

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Кастомный метод для получения пользователя по email
     */

    User findByEmail(String email);

}
