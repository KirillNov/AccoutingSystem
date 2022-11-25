package com.novikovkirill.test.accountingsystem.services;

import com.novikovkirill.test.accountingsystem.models.Appeal;
import com.novikovkirill.test.accountingsystem.models.Directory;
import lombok.NonNull;

import java.util.List;

public interface AppealService {

    List<Appeal> findAllByUser_Email(@NonNull String email);

    List<Appeal> findAll();


    Appeal findById(Long id);

    void save(Appeal appeal);

    void delete(Long id);



}
