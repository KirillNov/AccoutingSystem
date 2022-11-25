package com.novikovkirill.test.accountingsystem.repositories;


import com.novikovkirill.test.accountingsystem.models.Directory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для категорий
 */

public interface DirectoryRepository extends JpaRepository<Directory, Long> {
}
