package com.novikovkirill.test.accountingsystem.models;


import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс описывающий обращение
 * user - подтягивает данные пользователя который оставил обращение
 * date - дата когда было дано обращение
 * description - текст обращения
 * directory - категория для которой производитсяя обращение
 *
 */

@Data
@Entity
@Table(name = "appeal")
public class Appeal {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;


    private String date;

    @NotNull
    @Size(min = 10, max = 2000, message = "Длинна Вашего обращения должна быть не менее 10-ти символов и не более 2000")
    @NotBlank(message = "Введите своё обращение")
    private String description;


//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//    @JoinColumn
    @NotNull
    private String directory;

}
