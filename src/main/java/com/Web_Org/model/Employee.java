package com.Web_Org.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

//--------------------------------------------
//          Сущность - сотрудник
//--------------------------------------------
@Entity
@Table(name = "employee")
public class Employee {
    // Поля
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;             // Первичный ключ
    // !!!! По стандартам языка все лучше с маленькой буквы
    private String name;            // Имя
    private String surname;         // Фамилия
    private String otchest;         // Отчество

    // Отделы, в которых работает сотрудник
    @ManyToMany(
            fetch = FetchType.LAZY,     // Данные будут выгружаться только если их напрямую запросили
            cascade = {
                    CascadeType.PERSIST,   // При сохранении родителя, зависимый объект также сохранится
                    CascadeType.MERGE      // При обновлении родителя, зависимый объект также сохранится
            },
            mappedBy = "employees")  // Связь уже описана в другом классе, осуществляется через поле employees
    @JsonIgnore                     // Чтобы убрать зацикливание вызова связей при вызове функций получения элементов

    private Set<Department> departments = new HashSet<>();

    // Конструкторы
    public Employee(){          // !!! Обязательно должен быть конструктор по умолчанию, иначе спринг не сможет создавать экземпляры

    }
    public Employee(String p_name, String p_surname, String p_otchest) {
        name = p_name;
        surname = p_surname;
        otchest = p_otchest;
    }

    // Сеттеры для задания значений полей объекта
    public void setName(String p_Name){
        this.name = p_Name;
    }

    public void setId(Integer p_id) {
        this.id = p_id;
    }

    public void setSurname(String p_surname) {
        surname = p_surname;
    }

    public void setOtchest(String p_otchest) {
        otchest = p_otchest;
    }

    // Геттеры для получения значений полей объекта
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getOtchest() {
        return otchest;
    }


    // Геттеры и сеттеры для связующих объектов

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

}