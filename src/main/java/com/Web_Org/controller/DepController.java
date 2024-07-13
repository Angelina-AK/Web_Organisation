package com.Web_Org.controller;

import com.Web_Org.model.Department;
import com.Web_Org.model.Employee;
import com.Web_Org.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

//---------------------------------------------------------
//   Контроллер запросов для работы с табличкой отделов
//---------------------------------------------------------
@RestController
public class DepController {

    @Autowired
    private DepService depservice;


    // Отображение всех отделов
    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return depservice.getAllDepartments();
    }

    // Отображение отдела с таким id
    @GetMapping("/department/{id}")
    public Optional<Department> getDepartment(@PathVariable int id) {
        return depservice.getDepartment(id);
    }

    // Отображение сотрудников в отделе по id отдела
    @GetMapping("/department/{id}/employees")
    public ResponseEntity<List<Employee>> getAllEmployeesByDepartmentId(@PathVariable  (value = "id") int dep_id) {
        return depservice.getAllEmployeesByDepartmentId(dep_id);
    }

}
