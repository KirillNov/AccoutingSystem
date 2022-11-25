package com.novikovkirill.test.accountingsystem.models.validator;

import com.novikovkirill.test.accountingsystem.models.Directory;
import com.novikovkirill.test.accountingsystem.models.User;
import com.novikovkirill.test.accountingsystem.services.DirectoryService;
import com.novikovkirill.test.accountingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Класс в котором находится бизнес-логика аннотации CheckDirectoryName
 */

public class UniqueDirectoryConstraintValidator implements ConstraintValidator<CheckDirectoryName, String> {

    @Autowired
    private DirectoryService directoryService;

    @Override
    public void initialize(CheckDirectoryName checkDirectoryName) {

    }

    @Override
    public boolean isValid(String directory, ConstraintValidatorContext ctx) {
        List<Directory> directoryList = directoryService.findAll();
        for (Directory us: directoryList
        ) {
            if(directory.equals(us.getName())) {
                return false;
            }
        }

        return true;
    }
}
