package com.novikovkirill.test.accountingsystem.models.validator;

import com.novikovkirill.test.accountingsystem.models.User;
import com.novikovkirill.test.accountingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;

import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Класс в котором находится бизнес-логика аннотации CheckEmail
 */

public class UniqueEmailConstraintValidator implements ConstraintValidator<CheckEmail, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(CheckEmail checkEmail) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext ctx) {
        List<User> userList = userService.findAll();
        for (User us: userList
        ) {
            if(email.equals(us.getEmail())) {
                return false;
            }
        }

        return true;
    }
}
