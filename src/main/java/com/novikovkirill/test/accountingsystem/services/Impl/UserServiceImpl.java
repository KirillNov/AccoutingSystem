package com.novikovkirill.test.accountingsystem.services.Impl;

import com.novikovkirill.test.accountingsystem.models.User;

import com.novikovkirill.test.accountingsystem.models.enums.Role;
import com.novikovkirill.test.accountingsystem.models.enums.Status;
import com.novikovkirill.test.accountingsystem.repositories.UserRepository;
import com.novikovkirill.test.accountingsystem.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Сервис для пользователей работающий с БД
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Метод который служит для создания пользователя при регистрации.
     * Полям Role и Active присваиваются значения по умолчанию.
     * В будущем будет возможноть менять эти поля из панели Админа.
     */

    @Override
    public boolean createUser(User user) {
        String email = user.getEmail();
        if(userRepository.findByEmail(user.getEmail()) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.USER);
        log.info("Создан пользователь с почтой: {}", email);
        userRepository.save(user);
        return true;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}
