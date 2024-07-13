package com.Web_Org.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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
    //@JsonIgnore
    private Department superDepartment;     // !!! Нижние подчеркивания также плохо воспринимаются jpa

    // Подчиняющиеся
    @OneToMany (mappedBy = "superDepartment") // связь через поле superDepartment
    @JsonIgnore
    private  Set<Department> subDepartments = new HashSet<>();

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
    public Department(String name, Department superDepartment) {
        this.name = name;
        this.superDepartment = superDepartment;
    }


    // Сеттеры
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSuperDepartment(Department superDepartment) {
        this.superDepartment = superDepartment;
    }
    public void setSub_departments(Set<Department> subDepartments) {
        this.subDepartments = subDepartments;
    }

    // Геттеры
    public Department getSuperDepartment() {
        return superDepartment;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Set<Department> getsubDepartments() {
        return subDepartments;
    }

    // Геттеры и сеттеры для объектов связи c сотрудниками

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees( Set<Employee> employees) {
        this.employees = employees;
    }

}
