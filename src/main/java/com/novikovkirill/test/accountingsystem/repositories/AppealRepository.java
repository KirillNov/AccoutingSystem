package com.novikovkirill.test.accountingsystem.repositories;

import com.novikovkirill.test.accountingsystem.models.Appeal;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для обращений
 */

public interface AppealRepository extends JpaRepository<Appeal, Long > {

    /**
     * Кастомный метод для получения обращения по Email
     */

    List<Appeal> findAllByUser_Email(@NonNull String email);


}
