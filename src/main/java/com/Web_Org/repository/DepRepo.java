package com.Web_Org.repository;

import com.Web_Org.model.Department;
import com.Web_Org.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//--------------------------------------------------------------------
//  Наследуемый от CrudRepository интерфейс для таблички отделов
//      (для возможности создания структуры, через которую
//              происходит обращение к табличке)
//--------------------------------------------------------------------
public interface DepRepo extends CrudRepository<Department, Integer> {

    // Список сотрудников в отделе по id отдела
    List<Department> findDepartmentsByEmployeesId(Integer EmpId);

    // Список вышестоящих отеделов (в них будет содержаться информация о всех остальных)
    // признак самого главного отдела - отсутсвие заполненного поля "вышестоящий отдел"
    List<Department> findBySuperDepartmentIsNull();

}