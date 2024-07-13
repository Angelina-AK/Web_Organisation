package com.Web_Org.controller;

import com.Web_Org.model.Department;
import com.Web_Org.model.Employee;
import com.Web_Org.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

//---------------------------------------------------------
// Контроллер запросов для работы с табличкой сотрудников
//---------------------------------------------------------
@RestController
public class EmpController {


    @Autowired
    private EmpService empservice;

    // Отображение всех сотрудников
    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){
        return empservice.getAllEmployees();
    }

    // Отобржение сотрудника по id
    @GetMapping("/employee/{id}")
    public Optional<Employee> getEmployee(@PathVariable int id){
        return empservice.getEmployee(id);
    }

    // Отображение отедлов, в которых работает сотрудник, по id сотрудника
    @GetMapping("/employee/{id}/departments")
    public ResponseEntity<List<Department>> getAllDepartmentsByEmployeeId(@PathVariable  (value = "id") int emp_id) {
        return empservice.getAllDepartmentsByEmployeeId(emp_id);
    }


}
