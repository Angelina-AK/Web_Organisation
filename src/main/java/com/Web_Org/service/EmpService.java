package com.Web_Org.service;

import com.Web_Org.model.Department;
import com.Web_Org.model.Employee;
import com.Web_Org.repository.DepRepo;
import com.Web_Org.repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//-------------------------------------------------------------------
//      Сервис для  работы с табличкой сотрудников
//-------------------------------------------------------------------
@Service
public class EmpService {

    // Сотрудники
    @Autowired
    private EmpRepo emprepo;

    // Отделы
    @Autowired
    private DepRepo deprepo;

    // Получение всех сотрудников
    public List<Employee> getAllEmployees() {
        List<Employee> emps = (List<Employee>)emprepo.findAll();
        return emps;
    }

    // Поиск сотрудника по id
    public Optional<Employee> getEmployee(int id) {
        return emprepo.findById(id);
    }

    //_____________________________________________________________________________________________
    // Вывод отделов, в которых работает сотрудник по id сотрудника
    public ResponseEntity<List<Department>> getAllDepartmentsByEmployeeId(Integer employeeId) {
        // Если такого сотрудника нет, бессмысленно искать, в каких отделах он работает, ничего не возвращаем
        if (!emprepo.existsById(employeeId)) {
            return null;
        }
        // Получаем список отделов, в которых работает сотрудник
        List<Department> departments = deprepo.findDepartmentsByEmployeesId(employeeId);

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
    //_____________________________________________________________________________________________

}
