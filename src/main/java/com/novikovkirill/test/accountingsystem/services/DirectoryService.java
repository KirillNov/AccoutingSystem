package com.novikovkirill.test.accountingsystem.services;



import com.novikovkirill.test.accountingsystem.models.Directory;

import java.util.List;

public interface DirectoryService {

    List<Directory> findAll();

    void save(Directory directory);

    void delete(Long id);


}
