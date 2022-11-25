package com.novikovkirill.test.accountingsystem.services.Impl;

import com.novikovkirill.test.accountingsystem.models.Appeal;
import com.novikovkirill.test.accountingsystem.models.Directory;
import com.novikovkirill.test.accountingsystem.repositories.AppealRepository;
import com.novikovkirill.test.accountingsystem.services.AppealService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для обращений работающий с БД
 */

@Service
public class AppealServiceImpl implements AppealService {

    private final AppealRepository appealRepository;
    @Autowired
    public AppealServiceImpl(AppealRepository appealRepository) {
        this.appealRepository = appealRepository;
    }

    /**
     * Метода находящий обращение по email
     */

    @Override
    public List<Appeal> findAllByUser_Email(@NonNull String email) {
        return appealRepository.findAllByUser_Email(email);
    }

    /**
     * Метода находящий все обращение
     */

    @Override
    public List<Appeal> findAll() {
        return appealRepository.findAll();
    }

    /**
     * Метода находящий обращение по id с проверкой с помощью обертки optional
     */

    @Override
    public Appeal findById(Long id) {
        Appeal appeal = null;
        Optional<Appeal> optional = appealRepository.findById(id);
        if (optional.isPresent()) {
            appeal = optional.get();
        }
        return appeal;
    }

    @Override
    public void save(Appeal appeal) {
    appealRepository.save(appeal);
    }

    @Override
    public void delete(Long id) {
    appealRepository.deleteById(id);
    }
}
