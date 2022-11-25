package com.novikovkirill.test.accountingsystem.models;

import com.novikovkirill.test.accountingsystem.models.validator.CheckDirectoryName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Класс описывающий категории
 * name - название категории
 *
 */

@Entity
@Data
@Table(name = "directory")
public class Directory {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank(message = "Введите название новой категории")
    @Column(name = "name", unique = true)
    @Size(min = 2, max = 100, message = "Длинна категории не менее 2-х символов и не более 100")
    @CheckDirectoryName
    private String name;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "directory")
//    private List<Appeal> appealList;
}
