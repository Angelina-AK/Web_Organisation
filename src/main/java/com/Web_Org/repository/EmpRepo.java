package com.Web_Org.repository;

import com.Web_Org.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//--------------------------------------------------------------------
//  Наследуемый от CrudRepository интерфейс для таблички отрудников
//      (для возможности создания структуры, через которую
//              происходит обращение к табличке)
//--------------------------------------------------------------------
public interface EmpRepo extends CrudRepository<Employee,Integer> {

    // Список сотрудников в отделе по id отдела
    List<Employee> findEmployeesByDepartmentsId(Integer DepId);
}
