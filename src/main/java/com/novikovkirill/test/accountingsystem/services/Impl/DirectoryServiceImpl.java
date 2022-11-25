package com.novikovkirill.test.accountingsystem.services.Impl;


import com.novikovkirill.test.accountingsystem.models.Directory;
import com.novikovkirill.test.accountingsystem.models.User;
import com.novikovkirill.test.accountingsystem.repositories.DirectoryRepository;
import com.novikovkirill.test.accountingsystem.services.DirectoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Сервис для категорий работающий с БД
 */

@Service
@Slf4j
public class DirectoryServiceImpl implements DirectoryService {

    DirectoryRepository directoryRepository;
    @Autowired
    public DirectoryServiceImpl(DirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
    }

    /**
     * Метод находящий все категории
     */

    @Override
    public List<Directory> findAll() {
        return directoryRepository.findAll();
    }

    @Override
    public void save(Directory directory) {
        directoryRepository.save(directory);

    }

    @Override
    public void delete(Long id) {
        directoryRepository.deleteById(id);
    }

}
