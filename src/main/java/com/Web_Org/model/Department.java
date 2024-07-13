package com.Web_Org.model;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.Set;

//--------------------------------------------
//          Сущность - отдел
//--------------------------------------------
@Entity
public class Department {

    // Поля
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    // Вышестоящий отдел
    @ManyToOne
    @JoinColumn(name="sup_department_id")
    private Department sup_department;

    // Сотдруники отдела
    @ManyToMany(                                                    // Связь многие ко многим
            fetch = FetchType.LAZY,                                 //  данные будут выгружаться только если их напрямую запросили
            cascade = { CascadeType.PERSIST, CascadeType.MERGE }    // если сохраняется/обновляется родитель, то и зависимые объекты тоже
    )
    @JoinTable(                                                     // Описание связующей таблицы
            name = "department_employees",
            joinColumns = { @JoinColumn(name = "department_id") },  // Внешний ключ для ссылки на отдел
            inverseJoinColumns = {@JoinColumn(name = "employee_id") } // Внешний ключ для ссылки на сотрудника
    )
    private Set<Employee> employees = new HashSet<>();

    // Конструкторы
    public Department() {

    }
    public Department(String name, Department sup_department) {
        this.name = name;
        this.sup_department = sup_department;
    }


    // Сеттеры
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSup_department(Department sup_department) {
        this.sup_department = sup_department;
    }

    // Геттеры
    public Department getSup_department() {
        return sup_department;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    // Геттеры и сеттеры для объектов связи

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees( Set<Employee> employees) {
        this.employees = employees;
    }

}
