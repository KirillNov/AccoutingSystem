package com.novikovkirill.test.accountingsystem.models;

import com.novikovkirill.test.accountingsystem.models.enums.Role;
import com.novikovkirill.test.accountingsystem.models.validator.CheckEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс описывающий пользователя
 * name - имя пользователя
 * surname - фамилия пользователя
 * email - электронная почта пользователя
 * password - пароль, который в будущещм будет кодироваться
 * active - имеет тип boolean и показывает активен ли пользователь или нет. Можно добавить в панеле
 * администратора возможность банить по этому полю.
 * roles - Set из двух ролей (USER и ADMIN). У каждой роли свои доступы в приложении
 * createDate - дата регистрации
 * appeals - связь с обращениями
 */

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Введите ваше имя")
    @Size(min = 2, max = 50, message = "Длинна имени не менее 2-х символов и не более 50-ти")
    private String name;

    @Column(name = "surname")
    @NotBlank(message = "Введите вашу фамилию")
    @Size(min = 2, max = 50, message = "Длинна фамилии не менее 2-х символов и не более 50-ти")
    private String surname;

    @Column(name = "email", unique = true)
    @NotNull(message = "Введите вашу электронную почту")
    @CheckEmail
    private String email;

    @Column(name = "password", length = 1000)
    @NotNull
    @Size(min = 6, max = 100, message = "Длинна пароля не менее 6-ти символов и не более 100")
    private String password;

    @Column(name = "active")
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    private String createDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Appeal> appeals = new ArrayList<>();

    /**
     * Инициализация даты регистрации пользователя
     */


    @PrePersist
    private void init() {
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
        createDate = dateNow.format(format);
    }

    /**
     * Проверка роли пользователя, для отображения пользовательского интерфейса
     */

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    /**
     * Проверка прав доступа по ролям, для отображения пользовательского интерфейса
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
