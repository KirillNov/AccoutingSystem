package com.novikovkirill.test.accountingsystem.services.Impl;

import com.novikovkirill.test.accountingsystem.models.Appeal;
import com.novikovkirill.test.accountingsystem.models.Directory;
import com.novikovkirill.test.accountingsystem.models.User;
import com.novikovkirill.test.accountingsystem.models.enums.Role;
import com.novikovkirill.test.accountingsystem.repositories.AppealRepository;
import com.novikovkirill.test.accountingsystem.repositories.DirectoryRepository;
import com.novikovkirill.test.accountingsystem.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Сервис в котором заполняются наши таблицы в БД для тестирования приложения
 */

@Slf4j
@Service
public class InitService {

    UserRepository userRepository;
    AppealRepository appealRepository;
    DirectoryRepository directoryRepository;



    public InitService(UserRepository userRepository, AppealRepository appealRepository, DirectoryRepository directoryRepository) {
        this.userRepository = userRepository;
        this.appealRepository = appealRepository;
        this.directoryRepository = directoryRepository;
    }

    /**
     * Метод который служит для записи тестовых пользователей и админа в нашу БД при первом запуске приложения,
     * по этому метод помечен аннотацией @PostConstruct.
     * @PostConstruct , вызывается только один раз, сразу после инициализации свойств компонента
     */

    @PostConstruct
    private void addDirectoriesInDb() {
        log.info("Заполнение БД");

        List<Directory> directoryList = directoryRepository.findAll();
        log.info("DIRECTORY SIZE: {}", directoryList.size());

        if(directoryList.size() == 0) {
            Directory directory1 = new Directory();
            directory1.setName("ТСЖ");
            directoryRepository.save(directory1);

            Directory directory2 = new Directory();
            directory2.setName("Дороги");
            directoryRepository.save(directory2);

            Directory directory3 = new Directory();
            directory3.setName("Соц.служба");
            directoryRepository.save(directory3);


        }
        /***********************************************/

        List<User> userList = userRepository.findAll();
        log.info("USERS SIZE: {}", userList.size());
            if(userList.size() == 0) {

                User admin = new User();
                admin.setName("Admin");
                admin.setSurname("Admin");
                admin.setEmail("admin@admin.com");
                admin.setPassword("$2a$12$9kOJNRCOqSebCxCExLFZTeQRg98MV7HT/kmq.gnY7chutHxA.Kj/2");
                admin.setActive(true);
                admin.getRoles().add(Role.ADMIN);

                log.info("Пользователь ADMIN: {}", admin);
                userRepository.save(admin);

                User user = new User();
                user.setName("Kirill");
                user.setSurname("Novikov");
                user.setEmail("novikov@gmail.com");
                user.setPassword("$2a$12$e2P9u5j/pt/Fdf8HE1SN2uX3sTFcEUnKfwfelNoL45/qbIfdoVfwi");
                user.setActive(true);
                user.getRoles().add(Role.USER);

                log.info("Пользователь USER: {}", user);
                userRepository.save(user);

                User user2 = new User();
                user2.setName("Ivan");
                user2.setSurname("Ivanov");
                user2.setEmail("ivan@gmail.com");
                user2.setPassword("$2a$12$e2P9u5j/pt/Fdf8HE1SN2uX3sTFcEUnKfwfelNoL45/qbIfdoVfwi");
                user2.setActive(true);
                user2.getRoles().add(Role.USER);

                log.info("Пользователь USER: {}", user2);
                userRepository.save(user2);

                Appeal appeal1 = new Appeal();
                appeal1.setDirectory("ТСЖ");
                appeal1.setDescription("Просьба очистить снег с крыш домов по ул.Аэродромная");
                appeal1.setUser(user);
                appeal1.setDate("25-ноября-2022 00:08:25");

                appealRepository.save(appeal1);

                Appeal appeal2 = new Appeal();
                appeal2.setDirectory("Дороги");
                appeal2.setDescription("Просьба отремонтировать дорогу по ул.Аэродромная");
                appeal2.setUser(user);
                appeal2.setDate("23-ноября-2022 16:18:21");

                appealRepository.save(appeal2);

                Appeal appeal3 = new Appeal();
                appeal3.setDirectory("Соц.служба");
                appeal3.setDescription("Просьба обратить внимание на работу соц.службы по Советскому району");
                appeal3.setUser(user2);
                appeal3.setDate("24-ноября-2022 12:10:25");

                appealRepository.save(appeal3);

                Appeal appeal4 = new Appeal();
                appeal4.setDirectory("Дороги");
                appeal4.setDescription("Просьба отремонтировать дорогу по ул.Аврора");
                appeal4.setUser(user2);
                appeal4.setDate("25-ноября-2022 19:44:44");

                appealRepository.save(appeal4);



            }

        }

}
