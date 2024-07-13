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
//      Сервис для  работы с табличкой отделов
//-------------------------------------------------------------------
@Service
public class DepService {

    // Отделы
    @Autowired
    private DepRepo deprepo;

    // Сотрудники
    @Autowired
    private EmpRepo emprepo;

    // Получение всех отделов
    public List<Department> getAllDepartments() {
        List<Department> depts = (List<Department>)deprepo.findAll();
        return depts;
    }

    // Поиск отдела по id
    public Optional<Department> getDepartment(int id) {
        return deprepo.findById(id);
    }

    //_____________________________________________________________________________________________
    // Вывод сотрудников в отделе по id отдела
    public ResponseEntity<List<Employee>> getAllEmployeesByDepartmentId(Integer departmentId) {
        // Если такого отдела нет, бессмысленно искать сотрудников в нем
        if (!deprepo.existsById(departmentId)) {
            return null;
        }
        // Получаем список сотрудников в отделе
        List<Employee> employees = emprepo.findEmployeesByDepartmentsId(departmentId);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    //_____________________________________________________________________________________________
}
